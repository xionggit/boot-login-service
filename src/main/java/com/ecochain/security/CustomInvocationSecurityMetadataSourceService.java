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

import com.ecochain.service.AclUserService;

@Component
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOG = LoggerFactory.getLogger(CustomInvocationSecurityMetadataSourceService.class);

    private static Map<String, Collection<ConfigAttribute>> aclResourceMap = null;
    
    private AclUserService userClient;

    /**
     * 构造方法
     */
    public CustomInvocationSecurityMetadataSourceService(AclUserService userClient) {
        this.userClient = userClient;
        loadResourceDefine();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        System.out.println("=====getAttributes======");
        
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
        
        System.out.println("=====loadResourceDefine======");
        
        /**
         * 因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源
         */
        //TODO 因为前端无法传递token过来，暂时不错权限细化认证
//        List<Map<String, Object>> aclResourceses = userClient.selectAclResourcesTypeOfRequest();
        List<Map<String, Object>> aclResourceses = new ArrayList<Map<String,Object>>();
        aclResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for (Map<String, Object> aclResources : aclResourceses) {
            ConfigAttribute ca = new SecurityConfig(aclResources.get("authority").toString().toUpperCase());
            String url = aclResources.get("url").toString();
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
