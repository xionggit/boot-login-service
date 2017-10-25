package com.ecochain.service;

import java.util.List;

import com.ecochain.user.entity.AclResources;
import com.ecochain.user.entity.Auth;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclResourcesService {
    /**
     * 查询所有类型是 请求 的资源
     * 返回包括{ROLE_MODULE_CRUD}
     * 为了方法级权限控制
     */
    List<AclResources> selectAclResourcesTypeOfRequest();


    /**
     * 根据 resourceIds 逗号间隔字符串 查询资源
     */
    List<AclResources> selectAclResourcesByResourceIds(String resourceIds);

    /**
     * 登录返回页面url权限
     * @param ids
     * @return
     */
    List<Auth> findResourcesByResourceIds(String ids);

    /**
     * 
     * @return
     */
    List<AclResources> selectAllResources();
}
