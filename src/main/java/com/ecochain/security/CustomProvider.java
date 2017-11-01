package com.ecochain.security;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclUser;
import com.ecochain.user.entity.UserConstatnt;
import com.ecochain.util.DateUtil;

public class CustomProvider extends DaoAuthenticationProvider{
    
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    AclUserService userClient;
    
    @Value("${spring.dev_model}")
    private String devModel;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        log.debug("=======additionalAuthenticationChecks======");
        
        //判断用户是不是锁定
        
        //判断用户名、密码是否正确
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RuntimeException exception = new BadCredentialsException(messages.getMessage(
                "AbstractUserDetailsAuthenticationProvider.badCredentials",
                "Bad credentials"));
        // 下面是验证逻辑，验证通过则返回UsernamePasswordAuthenticationToken，
        // 否则，可直接抛出错误（AuthenticationException的子类，在登录验证不通过重定向
        // 至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息）
        try {
            /*
             * 1、读取用户信息
             * 2、判断是否锁定、是否过期、用户是否可用
             *    2.1 判断是否锁定是在SecurityUser的isAccountNonLocked方法重写的
             * 3、判断用户名、密码是否正确
             */
            return  super.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.debug("-----userName-----" + authentication.getName());
            // 锁定用户、密码检查等自定义设置
            
            AclUser aclUser = userClient.findAclUserByName(authentication.getName());
            if (null!=aclUser) {
                //超过5次，锁定用户30分钟，被锁定的用户通过跑定时任务解锁
                //根据需求改动，例如每天0点解锁
                //设置错误次数
                aclUser.setFailcount(aclUser.getFailcount()+1);
                if (aclUser.getFailcount() >= UserConstatnt.LOGIN_FAIL_COUNT) {
                    aclUser.setLocktime(new Date());
                    aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_YES);
                    exception = new LockedException(
                            messages.getMessage("Rock.locked", 
                                    new Object[]{UserConstatnt.LOGIN_LOCK_TIME}));
                } else {
                    
                    ((CustomUsernamePasswordAuthenticationToken) authentication).getHttpServletRequest()
                        .getSession().setAttribute("errorCount", aclUser.getFailcount());
                    
                    exception = new BadCredentialsException(e.getMessage() + ", "
                            + messages.getMessage("TimesLeft", 
                                    new Object[]{UserConstatnt.LOGIN_FAIL_COUNT - aclUser.getFailcount()}));
                }
                
                userClient.updateUserEntity(aclUser);
            }
        } catch (UsernameNotFoundException ex) {
            logger.debug("-----用户不存在用户----" + ex.getMessage());
            ex.printStackTrace();
            
            exception = ex;
        } catch (LockedException ex) {
            logger.debug("-----锁定用户----" + ex.getMessage());
            ex.printStackTrace();
            AclUser aclUser = userClient.findAclUserByName(authentication.getName());
            
            // diff 为负数，因为当前时间大于锁定时间
            int diff = DateUtil.getIntervalMinute(new Date(), aclUser.getLocktime());
            int minute = UserConstatnt.LOGIN_LOCK_TIME + diff;
            //通过锁定逻辑判断，尽量不要使minute 出现负数情况，减少判断
//            minute = minute > 0 ? minute : 0;
            
            exception = new LockedException(
                    messages.getMessage("Rock.locked", 
                            new Object[]{minute}));
        } 
        
        throw exception;
    }

}
