package com.ecochain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
* @Description 类说明: 
* @author xiong
* @GitConfig name: xionggit  email: shao200815@163.com
* @date 创建时间：2017-10-23 18:03:31
*/
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 200)
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        //Test node vimdiff-1
		93b77bcc1f3b

#         93b77bcc1f3b Merge branch 'test' of https://github.com/xionggit/boot-login-service into 
#         d7eb7087fca6 Merge branch 'test' of https://github.com/xionggit/boot-login-service into test                                                                                                                                       
#         386b250d4fe2 test nodepad□~Z~D  
    }
}
 