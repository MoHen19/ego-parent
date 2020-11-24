package com.ego.portal.controller;

import com.ego.common.result.BaseResult;
import com.ego.portal.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 验证码 Controller
 * @author MH19
 */
@Controller
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 验证码
     *
     * @param ticket
     * @param randStr
     * @return
     */
    @RequestMapping("captcha")
    @ResponseBody
    public BaseResult captcha(String ticket, String randStr) {
        return captchaService.captcha(ticket, randStr);
    }
}
