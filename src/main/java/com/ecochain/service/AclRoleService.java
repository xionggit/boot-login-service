package com.ecochain.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.config.RuntimeServiceException;
import com.ecochain.user.entity.AclRole;
import com.ecochain.user.entity.AclRoleSources;
import com.github.pagehelper.PageInfo;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRoleService {

    /**
     * 新增角色
     *
     * @param aclRole
     * @throws RuntimeServiceException
     */
    public void addAclRole(String stationName, int responseUserId, String[] ids,int userid) throws RuntimeServiceException;

    /**
     * 删除角色
     *
     * @throws RuntimeServiceException
     */
    public void deleteAclRole(int roleId,int userid) throws RuntimeServiceException;

    /**
     * 查询角色
     *
     * @return
     * @throws RuntimeServiceException
     */
    public AclRole queryAclRole() throws RuntimeServiceException;

    /**
     * 更新角色
     *
     * @param aclRole
     * @throws RuntimeServiceException
     */
    public void modifyAclRole(int roleId, String stationName, int responseUserId, String[] ids,int userid) throws RuntimeServiceException;

    /**
     * 查询所有角色
     * 支持分页
     *
     * @return
     * @throws RuntimeServiceException
     */
    public PageInfo<AclRole> queryAllAclRole(int pageNum, int pageSize);

    /**
     * 根据角色名称查询角色
     * 支持模糊查询，支持分页
     *
     * @return
     * @throws RuntimeServiceException
     */
    public PageInfo<AclRole> queryByRoleName(String roleName, int pageNum, int pageSize);

    /**
     * 查看岗位权限--角色资源表
     *
     * @return
     * @throws RuntimeServiceException
     */
    public AclRoleSources queryRRDetailById(int id);

    /**
     * 查看岗位权限--角色表
     *
     * @return
     * @throws RuntimeServiceException
     */
    public AclRole queryRDetailById(int id);

    /**
     * 查询所有岗位
     *
     * @return
     * @throws RuntimeServiceException
     */
    public List<AclRole> queryAllRole();
    
    /**
     * 修改岗位信息
     *
     * @return
     * @throws RuntimeServiceException
     */
    public void updateByExampleSelective(@Param("record") AclRole record);
    
    /**
     * 根据岗位id查询岗位信息
     * */
    public AclRole selectByPrimaryKey(Integer id);
    
    /**
     * 根据查询实体查询岗位列表
     * @param record 岗位查询实体
     * @return 岗位列表
     */
    public List<AclRole> queryRoleByEntity(AclRole record);

}