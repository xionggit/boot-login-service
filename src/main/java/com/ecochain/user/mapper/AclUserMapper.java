package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclUser;
import com.ecochain.user.entity.AclUserExample;

public interface AclUserMapper {
    int countByExample(AclUserExample example);

    int deleteByExample(AclUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclUser record);

    int insertSelective(AclUser record);

    List<AclUser> selectByExample(AclUserExample example);

    AclUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclUser record, @Param("example") AclUserExample example);

    int updateByExample(@Param("record") AclUser record, @Param("example") AclUserExample example);

    int updateByPrimaryKeySelective(AclUser record);

    int updateByPrimaryKey(AclUser record);
    
    List<AclUser> selectAllUserByResponserMobile(String mobile);
}