package com.ecochain.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Athos on 2016-10-06.
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //这里的BasePackage 多个目录使用,; 分割例如"com.ecochain.*.mapper,; com.ecochain.wallet.*.mapper"
        mapperScannerConfigurer.setBasePackage("com.ecochain.*.mapper,;" );
        //        Properties properties = new Properties();
        //        properties.setProperty("mappers", Mapper.class.getName());
        //        properties.setProperty("notEmpty", "false");
        //        properties.setProperty("IDENTITY", "MYSQL");
        //        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}