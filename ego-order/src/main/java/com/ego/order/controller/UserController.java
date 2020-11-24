package com.ego.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-25-20:53
 */
@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        return "redirect:"
                +request.getSession().getServletContext().getAttribute("portalUrl")
                +"/user/logout";
    }
}
