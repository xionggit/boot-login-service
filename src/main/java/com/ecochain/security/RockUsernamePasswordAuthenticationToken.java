package com.ecochain.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name:   email: 
* @date 创建时间：2017-10-24 19:29:09
*/
public class RockUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1615472466024090676L;

    public RockUsernamePasswordAuthenticationToken(String principal, String credentials, String vcode,String loginType,String rolePeople, HttpServletRequest request) {
        super(principal, credentials);
        this.vcode = vcode;
        this.loginType=loginType;
        this.rolePeople=rolePeople;
        this.request = request;
    }

    private String vcode;//验证码
    private String loginType;//安全员
    private String rolePeople;//商户代表

    private HttpServletRequest request;

    public String getVcode() {
        return vcode;
    }
    
    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getRolePeople() {
        return rolePeople;
    }

    public void setRolePeople(String rolePeople) {
        this.rolePeople = rolePeople;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public HttpServletRequest getHttpServletRequest() {
        return request;
    }
}
