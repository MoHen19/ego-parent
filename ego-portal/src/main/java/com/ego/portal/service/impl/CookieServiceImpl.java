package com.ego.portal.service.impl;

import com.ego.common.util.CookieUtil;
import com.ego.portal.service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MH
 * @site CookieService 实现类
 * @company
 * @create 2020-03-25-20:47
 */
@Service
public class CookieServiceImpl implements CookieService {

    /**
     * 设置cookie
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean setCookie(String ticket, HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.setCookie(request,response,"userTicket",ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取cookie
     * @param request
     * @return
     */
    @Override
    public String getCookie(HttpServletRequest request) {
        String userTicket = CookieUtil.getCookieValue(request,"userTicket");
        if (null != userTicket || null != userTicket.trim()){
            return userTicket;
        }
        return null;
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.deleteCookie(request,response,"userTicket");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
