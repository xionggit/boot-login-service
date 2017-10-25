package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclRoleSources;
import com.ecochain.user.entity.AclRoleSourcesExample;

public interface AclRoleSourcesMapper {
    long countByExample(AclRoleSourcesExample example);

    int deleteByExample(AclRoleSourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclRoleSources record);

    int insertSelective(AclRoleSources record);

    List<AclRoleSources> selectByExample(AclRoleSourcesExample example);

    AclRoleSources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclRoleSources record, @Param("example") AclRoleSourcesExample example);

    int updateByExample(@Param("record") AclRoleSources record, @Param("example") AclRoleSourcesExample example);

    int updateByPrimaryKeySelective(AclRoleSources record);

    int updateByPrimaryKey(AclRoleSources record);

    String selectResourceIdsByRoleIds(@Param("resourceIdsArray") String[] resourceIdsArray);

    int updateByRoleId(AclRoleSources record);

    int deleteByRoleId(Integer roleId);
    
    AclRoleSources selectByForeignKey(Integer roleId);
}