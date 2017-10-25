package com.ecochain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecochain.config.RuntimeServiceException;
import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclLog;
import com.ecochain.user.entity.AclUser;
import com.ecochain.user.entity.AclUserExample;
import com.ecochain.user.entity.CommonConstant;
import com.ecochain.user.entity.UserConstatnt;
import com.ecochain.user.entity.AclUserExample.Criteria;
import com.ecochain.user.mapper.AclLogMapper;
import com.ecochain.user.mapper.AclUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by Athos on 2016-07-02.
 */
@Service
public class AclUserServiceImpl implements AclUserService {
    @Autowired
    private AclUserMapper userMapper;
    @Autowired
    private AclLogMapper logMapper;
    private static final Logger LOG = LoggerFactory.getLogger(AclUserServiceImpl.class);

    /**
     * 更具用户名查询用户
     */
    @Override
    public AclUser findAclUserByName(String userName) throws RuntimeServiceException{

        AclUserExample example = new AclUserExample();
        Criteria criteria = example.createCriteria();
        
        if (Pattern.matches(CommonConstant.EMAIL_PATTERN, userName)) {
            criteria.andEmailEqualTo(userName);
        } else if (Pattern.matches(CommonConstant.MOBILE_PATTERN, userName)) {
            criteria.andMobileEqualTo(userName);
        }else{
            criteria.andUserNameEqualTo(userName);
        }
        
        List<AclUser> users = userMapper.selectByExample(example);
        if (null != users && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    /**
     * 分页方法
     */
    public PageInfo<AclUser> getAclUserByPage() {
        PageHelper.startPage(1, 10);
        AclUserExample example = new AclUserExample();
        List<AclUser> list = userMapper.selectByExample(example);
        PageInfo<AclUser> page = new PageInfo<AclUser>(list);
        LOG.error("分页码：" + page.getPageNum());
        LOG.error("分页大小：" + page.getPageSize());
        LOG.error("返回结果大小：" + page.getList().size());
        LOG.error("总数:" + page.getTotal());
        LOG.error("总页数：" + page.getPages());
        return page;
    }

    /**
     * 增加用户
     * 1.判断是否赋予什么样子的权限
     * 2.调用消管系统查询是否为商户代表
     * 3.调用安全员接口看是否是安全员
     */
    @Override
    public Integer addUser(AclUser aclUser) throws RuntimeServiceException{
        AclUserExample example = new AclUserExample();
        example.or().andUserNameEqualTo(aclUser.getUserName());
        List<AclUser> aclUserList = userMapper.selectByExample(example);
        if(aclUserList != null && aclUserList.size()>0){
        	throw new RuntimeServiceException("该手机号已经注册，请直接登录！");
        }else{
        	try{
	        	aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_NO);
	        	aclUser.setCreateTime(new Date());
	        	userMapper.insertSelective(aclUser);
	        	return aclUser.getId();
        	}catch(Exception e){
        		e.printStackTrace();
        		throw  new RuntimeServiceException("插入数据失败");
        	}
        }
    }

    /**
     * 修改密码
     *
     * @throws Exception
     */
    @Override
    public void forgetpassword(String realName, String mobilephone, String idcard, String password) throws RuntimeServiceException {
        AclUserExample example = new AclUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andRealnameEqualTo(realName).andMobileEqualTo(mobilephone).andIdcardEqualTo(idcard);
        List<AclUser> users = userMapper.selectByExample(example);
        if (null != users && users.size() == 1) {
            AclUser user = users.get(0);
          /*  if(user.getIslock().equals(UserConstatnt.ACLUSER_ISLOCK_YES)){
            	throw new RuntimeServiceException("该用户已经锁定！");
            }*/
            user.setUserPwd(password);
            user.setLastModifyTime(new Date());
            user.setIslock(UserConstatnt.ACLUSER_ISLOCK_NO);
            user.setFailcount(0);
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            throw new RuntimeServiceException("验证用户身份失败!");
        }
    }

    /**
     * 修改手机号
     */
    @Override
    public void updateMobile(String password, String oldmobilephone, String newmobilephone) throws RuntimeServiceException {
		AclUserExample example_new = new AclUserExample();
		example_new.createCriteria().andMobileEqualTo(newmobilephone);
		List<AclUser> list = userMapper.selectByExample(example_new);
		if(list != null && list.size() > 0){
			 throw new RuntimeServiceException("新手机号已经注册过");
		}else{
	        AclUserExample example = new AclUserExample();
	        Criteria criteria = example.createCriteria();
	        criteria.andMobileEqualTo(oldmobilephone);
	        List<AclUser> users = userMapper.selectByExample(example);
	        if (null != users && users.size() == 1) {
	            AclUser user = users.get(0);
	            user.setMobile(newmobilephone);
	            user.setUserPwd(password);
	            user.setUserName(newmobilephone);
	            user.setLastModifyTime(new Date());
	            userMapper.updateByPrimaryKeySelective(user);
	        } else {
	            throw new RuntimeServiceException("查找用户失败");
	        }
		}
    }

    /**
     * 修改密码
     * 先验证是否存在改用户
     */
    @Override
    public void updatePassword(String mobilephone, String oldpassword, String newpassword)
            throws RuntimeServiceException {
        AclUserExample example = new AclUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobilephone).andUserPwdEqualTo(oldpassword);
        List<AclUser> users = userMapper.selectByExample(example);
        if (null != users && users.size() == 1) {
            AclUser user = users.get(0);
            user.setUserPwd(newpassword);
            user.setLastModifyTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            throw new RuntimeServiceException("验证用户名和密码失败");
        }
    }

    @Override
    public void resetPwd(int userId, String newpassword) throws RuntimeServiceException {
        AclUser aclUser = new AclUser();
        aclUser.setId(userId);
        aclUser.setUserPwd(newpassword);
        aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_NO);
        aclUser.setFailcount(0);
        try {
            userMapper.updateByPrimaryKeySelective(aclUser);
        } catch (RuntimeServiceException e) {
            throw new RuntimeServiceException("重置密码失败！");
        }
    }

	@Override
	public AclUser lockUserByMobile(String mobile) throws RuntimeServiceException {
		AclUserExample example  =  new AclUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<AclUser>   list =userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			AclUser  aclUser =  list.get(0);
			aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_YES);
			Date lockTime = new Date();
			aclUser.setLocktime(lockTime);
			userMapper.updateByPrimaryKeySelective(aclUser);
			return aclUser;
		}else{
			throw new RuntimeServiceException("该用户不存在");
		}
		
	}

	@Override
	public void updateRenameAndidCard(String realName, String idCard,String mobile) throws RuntimeServiceException {
		AclUserExample example  =  new AclUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<AclUser>   list =userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			AclUser  aclUser =  list.get(0);
			aclUser.setIdcard(idCard);
			aclUser.setRealname(realName);
			userMapper.updateByPrimaryKeySelective(aclUser);
		}else{
			throw new RuntimeServiceException("该用户不存在");
		}
		
	}

	@Override
	public AclUser selectByPrimaryKey(Integer id) {
		AclUser aclUser = userMapper.selectByPrimaryKey(id);
		return aclUser;
	}

	@Override
	public void unLockUserByMobile(String mobile) throws RuntimeServiceException {
		AclUserExample example = new AclUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<AclUser> list = userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			AclUser aclUser = list.get(0);
			aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_NO);
			userMapper.updateByPrimaryKeySelective(aclUser);
		}else{
			throw new RuntimeServiceException("该用户不存在");
		}
	}

	@Override
	public void updateUserEntity(AclUser aclUser) {
		if (aclUser.getId() == null) {
			throw new RuntimeServiceException("用户ID不能为空！");
		}
		userMapper.updateByPrimaryKeySelective(aclUser);
	}
	
	@Override
	public List<AclUser> queryAclUserByMobiles(List<String> mobiles) {
		if (mobiles == null || mobiles.isEmpty()) {
			throw  new RuntimeServiceException("手机号不能为空！");
		}
		AclUserExample example = new AclUserExample();
		example.createCriteria().andUserNameIn(mobiles);
		return userMapper.selectByExample(example);
	}
	
	@Override
	public List<AclUser> queryAllUserByResponserMobile(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			throw  new RuntimeServiceException("手机号不能为空！");
		}
		
		return userMapper.selectAllUserByResponserMobile(mobile);
	}

	@Override
	public List<AclUser> selectByExample(String idNumber) {
		AclUserExample example = new AclUserExample();
		example.or().andIdcardEqualTo(idNumber);
		return userMapper.selectByExample(example);
	}

	@Override
	public AclUser findAclUserByusernameAndpassword(String userame, String password) throws RuntimeServiceException {
		AclUserExample example = new AclUserExample();
		example.createCriteria().andUserNameEqualTo(userame).andUserPwdEqualTo(password);
		List<AclUser> list =userMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}else{
			throw new RuntimeServiceException("登录密码或者手机号错误");
		}
	}

	@Override
	public void insertAclLog(AclLog aclLog) throws RuntimeServiceException {
		try{
			logMapper.insertSelective(aclLog);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeServiceException("记录登陆日志失败");
		}
		
	}

	@Override
	public AclUser queryAclUserByMobile(String mobile) throws RuntimeServiceException {
		AclUser aclUser = new AclUser();
		AclUserExample example = new AclUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<AclUser> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			aclUser=list.get(0);
		}
		return aclUser;
	}

	@Override
	public void deleteUserByUserName(String Username) throws RuntimeServiceException {
		try{
			AclUserExample example =  new AclUserExample();
			example.createCriteria().andUserNameEqualTo(Username);
			userMapper.deleteByExample(example);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeServiceException("删除用户失败");
		}
	}
}
