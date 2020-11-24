package com.ego.order.interceptor;

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
public class OrderCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.portal.url}")
    private String egoPortalUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletContext servletContext = request.getSession().getServletContext();
        String portalUrl = (String) servletContext.getAttribute("portalUrl");
        if (StringUtils.isEmpty(portalUrl)){
            servletContext.setAttribute("portalUrl",egoPortalUrl);
        }
        return true;
    }
}
