

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclUser;
import com.ecochain.user.entity.UserConstatnt;

public class MyProvider extends DaoAuthenticationProvider{
    
    Logger log = LoggerFactory.getLogger(getClass());
    
//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Autowired
    AclUserService userClient;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        System.out.println("=======additionalAuthenticationChecks======");
        authentication.getPrincipal();
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        // 这里通过authentication.getDetails()获取详细信息
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        
        System.out.println(details);
        String token = (String) details.getHttpServletRequest().getSession().getAttribute("token");
//        if(StringUtils.isBlank( token ) || !token.equals(details.getToken())){
//            throw new NonceExpiredException("验证码错误");
//        }
        
        // 下面是验证逻辑，验证通过则返回UsernamePasswordAuthenticationToken，
        // 否则，可直接抛出错误（AuthenticationException的子类，在登录验证不通过重定向至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息）
        try {
            return  super.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.debug("-----userName-----" + authentication.getName());
            // 锁定用户、密码检查等自定义设置
            RuntimeException exception = e;
            
            try {
                AclUser aclUser = userClient.findAclUserByName(authentication.getName());
                 if (null!=aclUser) {
                    //超过5次，锁定用户30分钟
                    if (aclUser.getFailcount() >= UserConstatnt.LOGIN_FAIL_COUNT) {
                        aclUser.setLocktime(new Date());
                        aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_YES);
                        exception = new BadCredentialsException("用户密码失败超过5次，已被锁定，等待30分钟后在尝试");
                    } else {
                        aclUser.setFailcount(aclUser.getFailcount()+1);
                        //设置错误次数
                        details.getHttpServletRequest().getSession().setAttribute("errorCount", aclUser.getFailcount());
                        
//                        exception = new BadCredentialsException(
//                                messages.getMessage("TimesLeft", new Object[]{UserConstatnt.LOGIN_FAIL_COUNT - aclUser.getFailcount()}));
                        exception = new BadCredentialsException(
                                "您还有" + (UserConstatnt.LOGIN_FAIL_COUNT - aclUser.getFailcount()) +"次机会");
                    }
                }
                 if(aclUser.getFailcount()==5){
                     aclUser.setLocktime(new Date());
                     aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_YES);
                 }
                 userClient.updateUserEntity(aclUser);
            } catch (Exception ex) {
                logger.debug("---------" + ex.getMessage());
                ex.printStackTrace();
            }
            throw exception;
        }
        
    }

}
