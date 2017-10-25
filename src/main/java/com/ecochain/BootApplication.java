package com.ecochain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.ecochain.security.CustomAuthenticationDetailsSource;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name:   email: 
* @date 创建时间：2017-10-23 18:03:31
*/
@SpringBootApplication
@EnableRedisHttpSession
public class BootApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootApplication.class, args);
        System.out.println(context.getBean(CustomAuthenticationDetailsSource.class));
    }
}
