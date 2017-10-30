package com.ecochain.user.entity;

import java.io.Serializable;

/** 
 * @Description: 类说明: user相关常量类，可以将该设置放置的数据进行动态配置。或者使用springconfig动态配置
 * @author: xiong  
 * @GitConfig name: xionggit  email: shao200815@163.com
 * @date: 2017-10-30 16:47:46 
 */
public class UserConstatnt implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6742491667867378273L;
    //用户已经锁定
    public final static String ACLUSER_ISLOCK_YES = "Y";
    //用户未被锁定
    public final static String ACLUSER_ISLOCK_NO = "N";
    //人员类型-商户代表
    public final static String PEOPLETYPE_BUSINESS_REPRESENTATIVE = "1";
    //人员类型-安全员
    public final static String PEOPLETYPE_SECURITY_OFFICER = "2";
    //人员类型-商户
    public final static String PEOPLETYPE_MERCHANT = "3";
    /**商户管理人**/
    public final static String PEOPLETYPE_MANAGER_MERCHANT = "4";
    /**门店管理人**/
    public final static String PEOPLETYPE_MANAGER_STORES = "5";
    
    //商户代表角色
    public final static String ROLEIDS_BUSINESS_REPRESENTATIVE = "1";
    //安全员角色
    public final static String ROLEIDS_SECURITY_OFFICER = "2";
    //商户角色
    public final static String ROLEIDS_MERCHANT = "3";
    //商户管理人角色
    public final static String ROLEIDS_MANAGER_MERCHANT = "5";
    //门店负责人角色
    public final static String ROLEIDS_MANAGER_STORES = "6";
    
    //登陆失败次数
    public final static Integer LOGIN_FAIL_COUNT = 5;
   //登陆锁定时间
    public final static Integer LOGIN_LOCK_TIME = 30;
    //图片锁定时间
    public final static Integer IMAGE_LOCK_TIME = 120;

    /**
     * 初始化
     */
    public final static String DEPARTMENT_FLAG_INIT = "01";
    /**
     * 后台增加	
     */
    public final static String DEPARTMENT_FLAG_ADD = "02";
    
    
    /**
     * 角色正常
     *      */
    public final static Integer ROLE_STATUS_DELETE_NO = 0;
    /**
     * 角色删除	
     */
    public final static Integer ROLE_STATUS_DELETE_YES = 1;

}
