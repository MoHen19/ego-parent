package com.ego.manager;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 扫描mapper接口
 * @MapperScan("com.ego.manager.mapper")
 * @author MH19
 */
@SpringBootApplication
@MapperScan("com.ego.manager.mapper")
@EnableDubboConfiguration
public class ManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class,args);
	}
}