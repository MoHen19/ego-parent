package com.ego.rpc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author MH
 * @site Rpc 启动类
 * @company
 * @create 2020-03-24-9:28
 * @EnableDubboConfiguration 开启dubbo
 */
@SpringBootApplication
@MapperScan("com.ego.rpc.mapper")
@EnableDubboConfiguration
public class RpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class,args);
    }
}
