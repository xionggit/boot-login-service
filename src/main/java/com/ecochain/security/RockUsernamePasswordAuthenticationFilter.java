package com.ecochain.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author haowei
 */
public class RockUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${spring.dev_model}")
    private String devModel;
//    @Autowired
//    private IUserAPIService userAPIService;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String vcode = request.getParameter("vcode");
        String loginType = request.getParameter("loginType");
        String rolePeople = request.getParameter("rolePeople");
        UsernamePasswordAuthenticationToken authRequest = new RockUsernamePasswordAuthenticationToken(username, password, vcode,loginType,rolePeople, request);
//        AclLogDto aclLogDto =  new AclLogDto();
//        aclLogDto.setLoginTime(new Date());
//        aclLogDto.setIp(SystemUtils.getIpAddr(request));
//        aclLogDto.setUserName(username);
//        aclLogDto.setBrowser(SystemUtils.getBrowser(request));
//        aclLogDto.setOs(SystemUtils.getRequestSystemInfo(request));
//        userAPIService.insertAclLog(aclLogDto);
        if (!"true".equalsIgnoreCase(devModel)) {

            if (request.getSession().getAttribute("errorCount") != null){
            
                String errorCount = request.getSession().getAttribute("errorCount").toString();
                
                if (Integer.parseInt(errorCount) >= 3){
            
                    RockUsernamePasswordAuthenticationToken rockAuthentication =
                        (RockUsernamePasswordAuthenticationToken) authRequest;
                    
//                    ValidationCode sessionVcode = (ValidationCode) rockAuthentication
//                            .getHttpServletRequest().getSession()
//                            .getAttribute("_validationCode");
                    
//                    if (sessionVcode == null
//                            || !sessionVcode.validate(rockAuthentication.getVcode())) {
//                        throw new InternalAuthenticationServiceException(
//                                messages.getMessage("WrongValidationCode",
//                                        "验证码输入有误或者验证码过期!"));
//                    }
                }
            }
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
