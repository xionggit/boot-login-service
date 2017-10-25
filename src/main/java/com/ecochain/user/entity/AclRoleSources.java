package com.ecochain.user.entity;

import java.util.Date;

public class AclRoleSources extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -4841149981292549191L;

    private Integer id;

    private Integer role_id;

    private Date create_time;

    private Date last_modify_time;

    private String resource_ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
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

    public String getResource_ids() {
        return resource_ids;
    }

    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids == null ? null : resource_ids.trim();
    }
}