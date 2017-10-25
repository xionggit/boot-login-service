package com.ecochain.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecochain.config.RuntimeServiceException;
import com.ecochain.service.AclRoleService;
import com.ecochain.user.entity.AclRole;
import com.ecochain.user.entity.AclRoleExample;
import com.ecochain.user.entity.AclRoleSources;
import com.ecochain.user.entity.UserConstatnt;
import com.ecochain.user.entity.AclRoleExample.Criteria;
import com.ecochain.user.mapper.AclRoleMapper;
import com.ecochain.user.mapper.AclRoleSourcesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
public class AclRoleServiceImpl implements AclRoleService {
    @Autowired
    AclRoleMapper roleMapper;
    
    @Autowired
    AclRoleSourcesMapper aclRoleSourcesMapper;

    @Override
    public void addAclRole(String stationName, int responseUserId, String[] ids,int userid) throws RuntimeServiceException {
        if(StringUtil.isEmpty(stationName)){
        	throw new RuntimeServiceException("岗位名称不能为空！");
        }
    	//判断岗位名称唯一
        AclRoleExample example = new AclRoleExample();
        example.createCriteria().andRole_nameEqualTo(stationName).andStatusEqualTo(UserConstatnt.ROLE_STATUS_DELETE_NO);
        List<AclRole> arList = roleMapper.selectByExample(example);
        if (arList != null && arList.size() > 0) {
            throw new RuntimeServiceException("当前岗位名称已存在！");
        }
		if(ids == null){
			ids = new String[0];
		}
        
        String pronoun = "ZZMC0001";//角色代名词
        AclRole role = roleMapper.selectByPronoun(pronoun);
        if (role != null) {
            String maxPronoun = roleMapper.getMaxPronoun();
            String str1 = maxPronoun.substring(4, maxPronoun.length());
            int str2 = Integer.parseInt(str1) + 1;
            if (String.valueOf(str2).length() == 1) {
                pronoun = "ZZMC000" + String.valueOf(str2);
            } else if (String.valueOf(str2).length() == 2) {
                pronoun = "ZZMC00" + String.valueOf(str2);
            } else if (String.valueOf(str2).length() == 3) {
                pronoun = "ZZMC0" + String.valueOf(str2);
            } else if (String.valueOf(str2).length() == 4) {
                pronoun = "ZZMC" + String.valueOf(str2);
            }
        }
        String resource_id = "";
        String resource_ids = "";
        for (int i = 0; i < ids.length; i++) {
        	resource_id += ids[i]+",";
        	resource_ids = resource_id.substring(0,resource_id.length()-1);
        }
        Date createTime = new Date();
        AclRole aclRole = new AclRole();
        aclRole.setRole_name(stationName);
        aclRole.setResponse_user_Id(responseUserId);
        aclRole.setCreate_time(createTime);
        aclRole.setUser_number(0);
        aclRole.setPronoun(pronoun);
        aclRole.setStatus(UserConstatnt.ROLE_STATUS_DELETE_NO);
        aclRole.setOperateid(userid);
        aclRole.setOperatetime(new Date());
        //此标识为新增员工岗位处所新增的
        aclRole.setDepartment_flag(UserConstatnt.DEPARTMENT_FLAG_ADD);
        try {
            roleMapper.insert(aclRole);
            int roleId = aclRole.getId();
            AclRoleSources aclRoleSources = new AclRoleSources();
            aclRoleSources.setRole_id(roleId);
            aclRoleSources.setResource_ids(resource_ids);
            aclRoleSources.setCreate_time(createTime);
            aclRoleSourcesMapper.insert(aclRoleSources);
        } catch (RuntimeServiceException e) {
            e.printStackTrace();
            throw new RuntimeServiceException("新增失败！");
        }
    }

    @Override
    public void deleteAclRole(int roleId,int userid) throws RuntimeServiceException {
        try {
            //roleMapper.deleteByPrimaryKey(roleId);
           // aclRoleSourcesMapper.deleteByRoleId(roleId);
        	AclRole record  =  new AclRole();
        	record.setId(roleId);
        	record.setStatus(UserConstatnt.ROLE_STATUS_DELETE_YES);
        	record.setOperateid(userid);
        	record.setOperatetime(new Date());
        	roleMapper.updateByPrimaryKeySelective(record);
        } catch (RuntimeServiceException e) {
            e.printStackTrace();
            throw new RuntimeServiceException("删除失败");
        }
    }

    @Override
    public AclRole queryAclRole() throws RuntimeServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modifyAclRole(int roleId, String stationName, int responseUserId, String[] ids,int userid) throws RuntimeServiceException {
        String resources_id = "";
        String resources_ids = "";
        for (int i = 0; i < ids.length; i++) {
        	resources_id += ids[i]+",";
        	resources_ids = resources_id.substring(0,resources_id.length()-1);
        }
        AclRole aRole = roleMapper.selectByPrimaryKey(roleId);
        Date lastModifyTime = new Date();
        AclRole aclRole = new AclRole();
        aclRole.setId(roleId);
        aclRole.setRole_name(stationName);
        aclRole.setPronoun(aRole.getPronoun());
        aclRole.setCreate_time(aRole.getCreate_time());
        aclRole.setLast_modify_time(lastModifyTime);
        aclRole.setUser_number(aRole.getUser_number());
        aclRole.setResponse_user_Id(responseUserId);
        aclRole.setStatus(UserConstatnt.ROLE_STATUS_DELETE_NO);
        aclRole.setOperateid(userid);
        aclRole.setOperatetime(new Date());
        AclRoleSources aclRoleSource = new AclRoleSources();
        aclRoleSource.setRole_id(roleId);
        aclRoleSource.setResource_ids(resources_ids);
        aclRoleSource.setLast_modify_time(lastModifyTime);
        try {
            roleMapper.updateByPrimaryKeySelective(aclRole);
            aclRoleSourcesMapper.updateByRoleId(aclRoleSource);
        } catch (RuntimeServiceException e) {
            e.printStackTrace();
            throw new RuntimeServiceException("更新失败！");
        }
    }

    @Override
    public PageInfo<AclRole> queryAllAclRole(int pageNum, int pageSize) {
        String roleName = "";
        PageInfo<AclRole> pageInfo = new PageInfo<AclRole>();
        pageInfo = queryByRoleName(roleName, pageNum, pageSize);
        return pageInfo;
    }

    @Override
    public PageInfo<AclRole> queryByRoleName(String roleName, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
        AclRoleExample example = new AclRoleExample();
        AclRoleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(UserConstatnt.ROLE_STATUS_DELETE_NO).andDepartment_flagEqualTo(UserConstatnt.DEPARTMENT_FLAG_ADD);
        if (StringUtils.isNotBlank(roleName)) {
        	// 模糊查询添加'%'
            criteria.andRole_nameLike("%"+ roleName + "%");
        }
        example.setOrderByClause("create_time DESC");
        List<AclRole> rList = roleMapper.selectByExample(example);
        PageInfo<AclRole> pageInfo = new PageInfo<AclRole>(rList);
        return pageInfo;
    }

    @Override
    public AclRoleSources queryRRDetailById(int roleId) {
        AclRoleSources aclRoleSources = aclRoleSourcesMapper.selectByForeignKey(roleId);
        return aclRoleSources;
    }

    @Override
    public AclRole queryRDetailById(int id) {
        AclRole aclRole = roleMapper.selectByPrimaryKey(id);
        return aclRole;
    }

    @Override
    public List<AclRole> queryAllRole() {
        AclRoleExample example = new AclRoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(UserConstatnt.ROLE_STATUS_DELETE_NO);
        criteria.andDepartment_flagEqualTo(UserConstatnt.DEPARTMENT_FLAG_ADD);
        List<AclRole> aclRoleList = roleMapper.selectByExample(example);
        return aclRoleList;
    }

	@Override
	public void updateByExampleSelective(AclRole record) {
//		AclRoleExample example = new AclRoleExample();
//        example.or().andIdEqualTo(record.getId());
        roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public AclRole selectByPrimaryKey(Integer id) {
		AclRole aclRole = roleMapper.selectByPrimaryKey(id);
		return aclRole;
	}
	
	@Override
	public List<AclRole> queryRoleByEntity(AclRole record) {
		if (record == null) {
			throw new RuntimeServiceException("查询实体不能为空！");
		}
		return roleMapper.selectByEntity(record);
	}
}