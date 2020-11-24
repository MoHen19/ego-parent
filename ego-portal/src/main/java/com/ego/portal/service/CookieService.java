package com.ego.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MH
 * @site CookieService
 * @company
 * @create 2020-03-25-20:44
 */
public interface CookieService {
    /**
     * 设置cookie
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    boolean setCookie(String ticket, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取cookie
     * @param request
     * @return
     */
    String getCookie(HttpServletRequest request);

    /**
     * 删除cookie
     * @param request
     * @param response
     * @return
     */
    boolean deleteCookie(HttpServletRequest request, HttpServletResponse response);
}
