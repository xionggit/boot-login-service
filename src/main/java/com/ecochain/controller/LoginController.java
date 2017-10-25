package com.ecochain.controller;

import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("rawtypes")
@RestController
public class LoginController{
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws SQLException{
        
        return new ModelAndView("login");
        
    }
    
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public String getToken(HttpServletRequest request) throws SQLException{
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute("token", token);
        return token;
        
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView getHello() throws SQLException{
        
        return new ModelAndView("hello");
    }
    
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() throws SQLException{
        
        return new ModelAndView("home");
        
    }
    
    @RequestMapping("/safetyOfficers")
    public ModelAndView safetyOfficers() throws SQLException{
        
        return new ModelAndView("home");
        
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws SQLException{
        
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = "/loginexpired", method = RequestMethod.GET)
    public String loginexpired() throws SQLException{
        
        return "被踢出了";
    }

}
