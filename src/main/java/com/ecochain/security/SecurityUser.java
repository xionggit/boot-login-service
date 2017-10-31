package com.ecochain.security;

import java.util.Collection;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecochain.user.entity.UserConstatnt;

/**
 * UserDetails对象
 * @Description: 类说明: TODO 不用的属性可以删除
 * @author: xiong  
 * @GitConfig name: xionggit  email: shao200815@163.com
 * @date: 2017-10-31 17:27:29 
 */
public class SecurityUser implements UserDetails {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String roleIds;//角色
    private String idcard;//身份证
    private String realname;//真实姓名
    private String peopletype;//人员类型
    private String mobile;//手机号
    private String username;//用户名
    private String password;//密码
    private boolean enabled = true;//是否启用 ，默认true
    private String locked;//是否锁定
    
    private Collection<? extends GrantedAuthority> authorities;
    
    public SecurityUser(Integer id,String username, String password,
            String locked, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.locked = locked;
        this.authorities = authorities;
    }
    

    public SecurityUser(Integer id, String username, String password, boolean enabled,
                        String roleIds, String idcard, String mobile, String peopletype,
                        String realname, String locked) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roleIds = roleIds;
        this.idcard = idcard;
        this.realname = realname;
        this.peopletype = peopletype;
        this.mobile = mobile;
        this.locked = locked;
    }

    public SecurityUser(Integer id, String username, String password, boolean enabled,
                        Collection<? extends GrantedAuthority> authorities,
                        String roleIds, String idcard, String mobile, String peopletype,
                        String realname, String locked) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.roleIds = roleIds;
        this.idcard = idcard;
        this.realname = realname;
        this.peopletype = peopletype;
        this.mobile = mobile;
        this.locked = locked;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserConstatnt.ACLUSER_ISLOCK_NO.equals(this.locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "MyUserDetails [id=" + id + ", username=" + username
                + ", password=" + password + ", enabled=" + enabled
                + ", authorities=" + authorities + "]";
    }

    public String getRoleIds() {
        return roleIds;
    }

    public String getIdcard() {
        return idcard;
    }
    
    public String getRealname() {
        return realname;
    }

    public String getPeopletype() {
        return peopletype;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SecurityUser that = (SecurityUser) o;

        return that.getUsername().equals(this.getUsername()) ? true : false;

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(roleIds)
                .append(idcard)
                .append(realname)
                .append(peopletype)
                .append(mobile)
                .append(username)
                .append(password)
                .append(enabled)
//                .append(responseMessage)
                .append(authorities)
                .toHashCode();
    }
}
