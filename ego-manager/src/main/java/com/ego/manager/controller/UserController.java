package com.ego.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.enums.BaseResultEnum;
import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.manager.service.CookieService;
import com.ego.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Reference(interfaceClass = SSOService.class)
    private SSOService ssoService;

    @Autowired
    private CookieService cookieService;

    /**
     * 用户登录
     * @param admin
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public BaseResult login(Admin admin, HttpServletRequest request, HttpServletResponse response, String verify){
        //获取验证码
        String pictureVerifyKey = (String) request.getSession().getAttribute("pictureVerifyKey");
        BaseResult baseResult = new BaseResult();
        //验证码是否一致,不一致返回提示信息
        if (StringUtils.isEmpty(verify) || !verify.trim().equals(pictureVerifyKey)){
            baseResult.setCode(BaseResultEnum.PASS_ERROR_03.getCode());
            baseResult.setMessage(BaseResultEnum.PASS_ERROR_03.getMessage());
            return baseResult;
        }
        //去单点登录系统验证用户信息返回票据信息
        String ticket = ssoService.login(admin);
        //判断票据信息是否为空,如果为空表示用户名或密码错误
        if (StringUtils.isEmpty(ticket)){
            baseResult.setCode(BaseResultEnum.PASS_ERROR_04.getCode());
            baseResult.setMessage(BaseResultEnum.PASS_ERROR_04.getMessage());
            return baseResult;
        }
        //把票据信息放入cookie,返回布尔结果
        boolean result = cookieService.setCookie(ticket, request, response);
        //将用户信息存入session,用户页面返显
        request.getSession().setAttribute("user",admin);
        return result?BaseResult.success():BaseResult.error();
    }

    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //通过request拿cookie的值,就是用户的ticket
        String ticket = cookieService.getCookie(request);
        //如果ticket不为空
        if (!StringUtils.isEmpty(ticket)){
            //清除redis
            ssoService.logout(ticket);
            //清除session
            request.getSession().removeAttribute("user");
            //清除cookie
            cookieService.deleteCookie(request,response);
        }
        return "login";
    }
}
