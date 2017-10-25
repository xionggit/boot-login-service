package com.ecochain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecochain.user.entity.AclRequestType;
import com.ecochain.user.entity.AclRequestTypeExample;

public interface AclRequestTypeMapper {
    long countByExample(AclRequestTypeExample example);

    int deleteByExample(AclRequestTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AclRequestType record);

    int insertSelective(AclRequestType record);

    List<AclRequestType> selectByExample(AclRequestTypeExample example);

    AclRequestType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AclRequestType record, @Param("example") AclRequestTypeExample example);

    int updateByExample(@Param("record") AclRequestType record, @Param("example") AclRequestTypeExample example);

    int updateByPrimaryKeySelective(AclRequestType record);

    int updateByPrimaryKey(AclRequestType record);

    String findPronounStrByRequestTypeIds(String requestTypeIds);
}