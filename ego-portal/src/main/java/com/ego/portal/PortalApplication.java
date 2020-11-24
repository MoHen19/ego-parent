package com.ego.portal;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-23-19:31
 */
@SpringBootApplication
@MapperScan("com.ego.portal.mapper")
@EnableDubboConfiguration
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class,args);
    }
}
