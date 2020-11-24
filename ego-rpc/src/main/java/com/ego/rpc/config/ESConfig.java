package com.ego.rpc.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author MH
 * @site elasticsearch 配置类
 * @company
 * @create 2020-03-25-12:24
 */
@Configuration
public class ESConfig {

    // ES服务器地址
    @Value("${elasticsearch.address}")
    private String[] address;

    // ES服务器连接方式
    private final static String SCHEME = "http";

    // 根据服务器地址构建 HttpHost 对象
    private HttpHost buildHttpHost(String s) {
        String[] address = s.split(":");
        if (2 != address.length) {
            return null;
        }
        String host = address[0];
        int port = Integer.parseInt(address[1]);
        return new HttpHost(host, port, SCHEME);
    }

    @Bean
    public RestClientBuilder restClientBuilder() {
        HttpHost[] hosts = Arrays.stream(address)
                .map(this::buildHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        return RestClient.builder(hosts);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }
}
