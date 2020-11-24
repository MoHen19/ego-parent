package com.ego.portal.config;

import com.ego.portal.interceptor.PortalCommonInterceptor;
import com.ego.portal.interceptor.PortalLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MH
 * @site 拦截器配置类
 * @company
 * @create 2020-03-25-21:18
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private PortalLoginInterceptor loginInterceptor;

    @Autowired
    private PortalCommonInterceptor commonInterceptor;

    /**
     * 拦截器配置
     *  addInterceptor:添加自定义拦截器
     *  addPathPatterns：添加拦截请求
     *  /*: 只拦截后面一级
     *  /**: 拦截所有
     *  excludePathPatterns：不拦截的请求
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/cart/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/logout/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
