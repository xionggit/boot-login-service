package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclLog;
import com.ecochain.user.entity.AclLogExample;

public interface AclLogMapper {
    int countByExample(AclLogExample example);

    int deleteByExample(AclLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclLog record);

    int insertSelective(AclLog record);

    List<AclLog> selectByExample(AclLogExample example);

    AclLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclLog record, @Param("example") AclLogExample example);

    int updateByExample(@Param("record") AclLog record, @Param("example") AclLogExample example);

    int updateByPrimaryKeySelective(AclLog record);

    int updateByPrimaryKey(AclLog record);
}