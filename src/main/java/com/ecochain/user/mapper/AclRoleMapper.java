package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclRole;
import com.ecochain.user.entity.AclRoleExample;

public interface AclRoleMapper {
    int countByExample(AclRoleExample example);

    int deleteByExample(AclRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclRole record);

    int insertSelective(AclRole record);

    List<AclRole> selectByExample(AclRoleExample example);

    AclRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclRole record, @Param("example") AclRoleExample example);

    int updateByExample(@Param("record") AclRole record, @Param("example") AclRoleExample example);

    int updateByPrimaryKeySelective(AclRole record);

    int updateByPrimaryKey(AclRole record);
    
    AclRole selectByPronoun(String pronoun);

    String getMaxPronoun();
    
    List<AclRole> selectByEntity(AclRole record);
}