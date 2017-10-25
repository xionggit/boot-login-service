package com.ecochain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecochain.service.AclRequestTypeService;
import com.ecochain.user.entity.AclRequestType;
import com.ecochain.user.mapper.AclRequestTypeMapper;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
public class AclRequestTypeServiceImpl implements AclRequestTypeService {

    @Autowired
    AclRequestTypeMapper typeMapper;

    protected AclRequestTypeMapper getMapper() {
        //        return super.getMapper(AclRequestType.class);
        return typeMapper;
    }

    @Override
    public String findPronounStrByRequestTypeIds(String requestTypeIds) {
        return this.getMapper().findPronounStrByRequestTypeIds(requestTypeIds);
    }

    @Override
    public int selectCountByNeId(AclRequestType aclRequestType) {
        return 0;
    }

    @Override
    public AclRequestType getEntityById(int i) {
        return getMapper().selectByPrimaryKey(i);
    }
}
