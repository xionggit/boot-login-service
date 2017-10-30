/**
 *
 */
package com.ecochain.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.ecochain.util.ValidationCode;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${spring.dev_model}")
    private String devModel;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        /*
         *  TODO 这里反向解密password或者，通过双层加密方式，来确保http请求不明文传递密码
         *  防止网管等人通过访问信息截取用户文明密码
         */
//        if (!StringUtils.isEmpty(password)) {
//        	// Base64解密
//        	password = new String(Base64.decodeBase64(password));
//        }
        
        String vcode = request.getParameter("token");//自定义参数
        String loginType = request.getParameter("loginType");//自定义参数
        String rolePeople = request.getParameter("rolePeople");//自定义参数
        UsernamePasswordAuthenticationToken authRequest = new CustomUsernamePasswordAuthenticationToken(username, password, vcode,loginType,rolePeople, request);
        //TODO 登陆日志
//        AclLogDto aclLogDto =  new AclLogDto();
//        aclLogDto.setLoginTime(new Date());
//        aclLogDto.setIp(SystemUtils.getIpAddr(request));
//        aclLogDto.setUserName(username);
//        aclLogDto.setBrowser(SystemUtils.getBrowser(request));
//        aclLogDto.setOs(SystemUtils.getRequestSystemInfo(request));
//        userAPIService.insertAclLog(aclLogDto);
        
        //不是测试环境,并且密码错误次数超过3次则开启验证码校验
        if (!"true".equalsIgnoreCase(devModel)) {
            
            int errorCount = 0 ;

            if (request.getSession().getAttribute("errorCount") != null){
                errorCount = (int) request.getSession().getAttribute("errorCount");
            }
                
            if (errorCount >= 3){
        
                CustomUsernamePasswordAuthenticationToken rockAuthentication =
                    (CustomUsernamePasswordAuthenticationToken) authRequest;
                
                ValidationCode sessionVcode = (ValidationCode) rockAuthentication
                        .getHttpServletRequest().getSession()
                        .getAttribute("_validationCode");
                
                if (sessionVcode == null
                        || !sessionVcode.validate(rockAuthentication.getVcode())) {
                    throw new NonceExpiredException(
                            messages.getMessage("WrongValidationCode",
                                    "验证码输入有误或者验证码过期!"));
                }
            }
            
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
