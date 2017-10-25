package com.ecochain.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        
       /**
         * 修改调用 mc-user里面的接口queryByName
         */
        log.info("userNmae :"+ username);
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        /**
         * 修改调用mc-user/selectResourceIdsByRoleIds
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
            
            
//            if (resourceResultDto != null){
//                
//                List<AclResources> aclResourcesList = resourceResultDto;
//                for (AclResources aclResources : aclResourcesList) {
//                    auths.add(new SimpleGrantedAuthority(aclResources.getAuthority().toString().toUpperCase()));
//                }
//            }
            SecurityUser securityUser = new SecurityUser(user.getId(), user.getUserName(), user.getUserPwd(),
                    true, auths, user.getRoleIds(), user.getIdcard(),
                    user.getMobile(), user.getPeopletype(), user.getRealname());
//            return new User(user.getUserName().toLowerCase(),
//                    user.getUserPwd().toLowerCase(),true,true,true,true,auths);

            return securityUser;
        }else{
            throw new UsernameNotFoundException(username + " not found");
        }
        
    }

}
