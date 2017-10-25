package com.ecochain.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     * 
     */
    private static final long serialVersionUID = -2854239865971937346L;
    private final String token;
    private HttpServletRequest request;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.request = request;
        token = request.getParameter("token");
    }
    
    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Token: ").append(this.getToken());
        return sb.toString();
    }
}
