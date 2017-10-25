package com.ecochain.user.entity;

import java.io.Serializable;

public class Auth implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -353590988570141181L;

    private String level1;
    
    private String level2;
    
    private String url;
    
    private String code;

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
