package com.ecochain.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecochain.service.AclRoleResourcesService;
import com.ecochain.user.mapper.AclRoleSourcesMapper;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
public class AclRoleResourcesServiceImpl implements AclRoleResourcesService {

    @Autowired
    AclRoleSourcesMapper sourcesMapper;

    @Override
    public String selectResourceIdsByRoleIds(String roleIds) {
        //        AclRoleResources resources = this.getMapper().selectResourceIdsByRoleIds(roleIds);
        String resourceIds = sourcesMapper.selectResourceIdsByRoleIds(roleIds.split(","));
        String[] resourceIdsArray = resourceIds.split(",");
        ArrayList<String> list = new ArrayList<String>();
        for (String resourceId : resourceIdsArray) {
            if (!list.contains(resourceId)) {
                list.add(resourceId);
            }
        }
        return StringUtils.join(list.toArray(), ",");
    }
}
