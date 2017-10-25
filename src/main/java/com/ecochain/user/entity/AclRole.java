package com.ecochain.user.entity;

import java.util.Date;

public class AclRole  extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -7806044828052136822L;

    private Integer id;

    private String role_name;

    private String pronoun;

    private Date create_time;

    private Date last_modify_time;

    private Integer user_number;

    private String department_flag;

    private Integer response_user_Id;

    private Integer status;

    private Integer operateid;

    private Date operatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name == null ? null : role_name.trim();
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun == null ? null : pronoun.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public Integer getUser_number() {
        return user_number;
    }

    public void setUser_number(Integer user_number) {
        this.user_number = user_number;
    }

    public String getDepartment_flag() {
        return department_flag;
    }

    public void setDepartment_flag(String department_flag) {
        this.department_flag = department_flag == null ? null : department_flag.trim();
    }

    public Integer getResponse_user_Id() {
        return response_user_Id;
    }

    public void setResponse_user_Id(Integer response_user_Id) {
        this.response_user_Id = response_user_Id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperateid() {
        return operateid;
    }

    public void setOperateid(Integer operateid) {
        this.operateid = operateid;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }
}