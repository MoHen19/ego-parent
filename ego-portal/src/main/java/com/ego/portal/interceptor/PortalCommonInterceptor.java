package com.ego.portal.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-27-22:21
 */
@Component
public class PortalCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.order.url}")
    private String egoOrderUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletContext servletContext = request.getSession().getServletContext();
        String orderUrl = (String) servletContext.getAttribute("orderUrl");
        if (StringUtils.isEmpty(orderUrl)){
            servletContext.setAttribute("orderUrl",egoOrderUrl);
        }
        return true;
    }
}
