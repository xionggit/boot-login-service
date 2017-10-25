package com.ecochain.service;

import com.ecochain.user.entity.AclRequestType;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRequestTypeService {
    String findPronounStrByRequestTypeIds(String requestTypeIds);

    int selectCountByNeId(AclRequestType aclRequestType);

    AclRequestType getEntityById(int i);
}
