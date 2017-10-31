package com.ecochain.task;
/**
* @Description 定时任务解锁用户
* @author 作者 E-mail: xiong
* @GitConfig name: xionggit  email: shao200815@163.com
* @date 创建时间：2017-10-31 19:03:34
*/

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclUser;

public class UnLockUserTask {

    private static Logger log = LoggerFactory.getLogger(UnLockUserTask.class);
    
    @Autowired
    private AclUserService aclUserService;
    
    @Scheduled(cron = "0 0/1 * * * ? ")// 每分钟执行
    public void unLockUser() {
        
        List<AclUser> aclUsers = aclUserService.getNeedLockedUser();
        if (aclUsers != null) {
            for (AclUser aclUser : aclUsers) {
                log.info("unLockUser ==" + aclUser.getUserName());
                aclUserService.unLockUserByMobile(aclUser.getMobile());
            }
        }
        
    }
    
}
