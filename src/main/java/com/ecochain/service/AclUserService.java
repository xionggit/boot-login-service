package com.ecochain.service;

import java.util.List;

import com.ecochain.config.RuntimeServiceException;
import com.ecochain.user.entity.AclLog;
import com.ecochain.user.entity.AclUser;
import com.github.pagehelper.PageInfo;

/**
 * Created by Athos on 2016-07-02.
 */
public interface AclUserService {
    /**
     * 
     * 通过正则匹配，邮箱或手机号或用户名查询用户
     * 匹配顺序  邮箱>手机号>用户名
     * @param userName
     * @return
     */
    AclUser findAclUserByName(String userName);

    /**
     * 测试分页方法
     *
     * @return
     */
    public PageInfo<AclUser> getAclUserByPage();

    /**
     * 注册账号
     *
     * @param aclUser
     */
    public Integer addUser(AclUser aclUser)throws RuntimeServiceException;

    /**
     * 忘记密码s
     *
     * @param aclUser
     */
    public void forgetpassword(String realName, String mobilephone, String idcard, String password) throws RuntimeServiceException;

    /**
     * 修改手机号
     *
     * @param realName
     * @param mobilephone
     * @param idcard
     * @param password
     * @throws RuntimeServiceException
     */
    public void updateMobile(String password, String oldmobilephone, String newmobilephone) throws RuntimeServiceException;

    /**
     * 修改密码
     *
     * @param mobilephone
     * @param oldpassword
     * @param newpassword
     * @throws RuntimeServiceException
     */
    public void updatePassword(String mobilephone, String oldpassword, String newpassword) throws RuntimeServiceException;

    /**
     * 重置密码
     *
     * @param userId
     * @param newpassword
     * @throws RuntimeServiceException
     */
    public void resetPwd(int userId, String newpassword) throws RuntimeServiceException;
    /**
     * 锁住用户
     * @param mobile
     * @throws RuntimeServiceException
     * @return AclUser
     */
    public  AclUser  lockUserByMobile(String mobile) throws RuntimeServiceException;
    /**
     * 解锁用户
     * @param mobile
     * @throws RuntimeServiceException
     */
    public  void  unLockUserByMobile(String mobile) throws RuntimeServiceException;
    
    /**  
     * 获取被锁定时间超过30分钟，需要解锁的用户，
     * @Title getNeedLockedUser  
     * @return List<AclUser>   
     */  
    public List<AclUser> getNeedLockedUser();
    /**
     * 修改用户信息
     * @param mobile
     * @param realName
     * @param idCard
     * @throws RuntimeServiceException
     */
    public void updateRenameAndidCard(String realName, String idCard,String mobile) throws RuntimeServiceException ;
    /**
     * 通过id获取详情
     * */
    public AclUser selectByPrimaryKey(Integer id);
    
    /**
     * 更新用户实体信息
     * @param aclUser 用户实体
     */
    public void updateUserEntity(AclUser aclUser);
    
    /**  
     * 通过username更新AclUser里不为null的字段
     * @Title updateUserByUserName  
     * @param aclUser void   
     */  
    public void updateUserByUserName(AclUser aclUser);
    
    /**
     * 根据手机号列表查询满足条件的用户信息
     * @param mobiles 手机号列表
     * @return 用户列表
     */
    public List<AclUser> queryAclUserByMobiles(List<String> mobiles);
    
    /**
     * 若该手机号为岗位负责人，查询该岗位负责人管辖岗位下的的所有人列表
     * @param mobile 手机号
     * @return
     */
    public List<AclUser> queryAllUserByResponserMobile(String mobile);
    
    /**
     * 验证身份证号在用户表中是否已经存在
     * */
    public List<AclUser> selectByExample(String idNumber);
    
    public AclUser  findAclUserByusernameAndpassword(String userame,String password)throws RuntimeServiceException;
    /**
     * 记录登陆日志
     * @param aclLog
     * @throws RuntimeServiceException
     */
    public void insertAclLog(AclLog  aclLog) throws RuntimeServiceException;
    
    public AclUser queryAclUserByMobile(String mobile) throws RuntimeServiceException;
    /**
     * 删除用户
     * @param Username
     * @throws RuntimeServiceException
     */
    public void deleteUserByUserName(String Username) throws RuntimeServiceException;
}
