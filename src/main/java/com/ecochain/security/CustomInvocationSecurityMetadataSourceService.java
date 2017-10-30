package com.ecochain.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.ecochain.service.AclResourcesService;
import com.ecochain.user.entity.AclResources;

@Component
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOG = LoggerFactory.getLogger(CustomInvocationSecurityMetadataSourceService.class);

    private static Map<String, Collection<ConfigAttribute>> aclResourceMap = null;
    
    private AclResourcesService aclResourcesService;

    /**
     * 构造方法
     */
    public CustomInvocationSecurityMetadataSourceService(AclResourcesService userClient) {
        this.aclResourcesService = userClient;
        loadResourceDefine();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        LOG.debug("=====getAttributes======");
        
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Iterator<String> ite = aclResourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(request)) {
                return aclResourceMap.get(resURL);
            }
        }
        return null;
    }

    //4
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        LOG.info("------------metadata : getAllConfigAttributes");
        return null;
    }

    //3
    @Override
    public boolean supports(Class<?> clazz) {
        LOG.info("------------metadata : supports");
        return true;
    }


    private void loadResourceDefine() {
        
        LOG.debug("=====loadResourceDefine======");
        
        /**
         * 因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源
         * 从数据库读取需要权限的url
         */
        List<AclResources> aclResourceses = aclResourcesService.selectAclResourcesTypeOfRequest();
        //如果不想从数据库配置，这里直接设置为new ArrayList<Map<String,Object>>();即可。
        //然后从代码里配置@PreAuthorize("")
//        List<Map<String, Object>> aclResourceses = new ArrayList<Map<String,Object>>();
        aclResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for (AclResources aclResources : aclResourceses) {
            ConfigAttribute ca = new SecurityConfig(aclResources.getAuthority().toUpperCase());
            String url = aclResources.getUrl().toString();
            if (aclResourceMap.containsKey(url)) {
                Collection<ConfigAttribute> value = aclResourceMap.get(url);
                value.add(ca);
                aclResourceMap.put(url, value);

            } else {
                Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                atts.add(ca);
                aclResourceMap.put(url, atts);
            }
        }
    }
}
