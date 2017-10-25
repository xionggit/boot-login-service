package com.ecochain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecochain.service.AclResourcesService;
import com.ecochain.user.entity.AclResources;
import com.ecochain.user.entity.AclResourcesExample;
import com.ecochain.user.entity.Auth;
import com.ecochain.user.mapper.AclResourcesMapper;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
public class AclResourcesServiceImpl implements AclResourcesService {


    @Autowired
    AclResourcesMapper resourcesMapper;

    protected AclResourcesMapper getMapper() {
        return resourcesMapper;
    }

    @Override
    public List<AclResources> selectAclResourcesTypeOfRequest() {
        return getMapper().selectAclResourcesTypeOfRequest();
    }

    @Override
    public List<AclResources> selectAclResourcesByResourceIds(String resourceIds) {
        return getMapper().selectAclResourcesByResourceIds(resourceIds.split(","));
    }
    
    @Override
    public List<Auth> findResourcesByResourceIds(String ids) {
        return resourcesMapper.findResourcesByResourceIds(ids.split(","));
    }

    @Override
    public List<AclResources> selectAllResources() {
        return resourcesMapper.selectByExample(new AclResourcesExample());
    }
}
