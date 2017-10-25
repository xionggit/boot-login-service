package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclResources;
import com.ecochain.user.entity.AclResourcesExample;
import com.ecochain.user.entity.Auth;

public interface AclResourcesMapper {
    long countByExample(AclResourcesExample example);

    int deleteByExample(AclResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclResources record);

    int insertSelective(AclResources record);

    List<AclResources> selectByExample(AclResourcesExample example);

    AclResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclResources record, @Param("example") AclResourcesExample example);

    int updateByExample(@Param("record") AclResources record, @Param("example") AclResourcesExample example);

    int updateByPrimaryKeySelective(AclResources record);

    int updateByPrimaryKey(AclResources record);

    /**
     * 查询所有类型是 请求 的资源
     * 返回包括{ROLE_MODULE_CRUD}
     * 为了方法级权限控制
     */
    List<AclResources> selectAclResourcesTypeOfRequest();

    /**
     * 根据类型查询资源
     * 返回不包括{ROLE_MODULE_CRUD}
     */
    List<AclResources> selectAclResourcesByType(String type);

    /**
     * 根据 resourceIdsArray 资源ID数组 查询资源
     */

    List<AclResources> selectAclResourcesByResourceIds(@Param("resourceIdsArray") String[] resourceIdsArray);

    /**
     * 根据 resourceIdsArray 资源ID数组 查询资源返回页面url
     * @param split
     * @return
     */
    List<Auth> findResourcesByResourceIds(@Param("resourceIdsArray") String[] resourceIdsArray);
}