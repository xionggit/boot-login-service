package com.ecochain.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecochain.service.AclResourcesService;
import com.ecochain.service.AclRoleResourcesService;
import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclResources;
import com.ecochain.user.entity.AclUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    AclUserService userClient;
    
    @Autowired
    AclResourcesService aclResourcesService;
    
    @Autowired
    AclRoleResourcesService aclRoleResourcesService;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        log.info("userNmae :"+ username);
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        /**
         * 通过数据库查询用户信息
         */
        AclUser user = userClient.findAclUserByName(username);
        
        if ( user != null ){
            
            
            List<AclResources> resourceResultDto = null;
            String resourceIds = aclRoleResourcesService.selectResourceIdsByRoleIds(user.getRoleIds());
            
            if (resourceIds != null) {
                resourceResultDto = aclResourcesService.selectAclResourcesByResourceIds(resourceIds);
                
            }else{
                resourceResultDto = null;
            }
            
            
            if (resourceResultDto != null){
                
                List<AclResources> aclResourcesList = resourceResultDto;
                for (AclResources aclResources : aclResourcesList) {
                    auths.add(new SimpleGrantedAuthority(aclResources.getAuthority().toString().toUpperCase()));
                }
            }
            SecurityUser securityUser = new SecurityUser(user.getId(), user.getUserName(), user.getUserPwd(),
                    true, auths, user.getRoleIds(), user.getIdcard(),
                    user.getMobile(), user.getPeopletype(), user.getRealname(),user.getIslock());

            return securityUser;
        }else{
            /*
             * TODO 根据安全级别定义是丢出 UsernameNotFoundException 还是BadCredentialsException
             * 
             * 不建议抛出UsernameNotFoundException是因为没有做IP限制登录，会导致用户名被遍历，
             * 使竞争对手可以获取平台用户信息
             */
            throw new UsernameNotFoundException(username + " not found");
        }
        
    }

}
