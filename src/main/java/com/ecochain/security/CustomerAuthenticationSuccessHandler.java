package com.ecochain.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name: shaotianxiong  email: nobita_shao@eco-chain.com
* @date 创建时间：2017-10-23 16:00:54
*/
public class CustomerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
      //例1：不跳到XML设定的页面，而是直接返回json字符串
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
//        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("realname", securityUser.getRealname());
//        map.put("peopletype", securityUser.getPeopletype());
//        map.put("username", securityUser.getUsername());
//        map.put("mobile", securityUser.getMobile());
        map.put("code", 200);
        map.put("message", "success");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(map));
    }
    
}
