package com.ego.portal.service;

import com.ego.common.result.BaseResult;

/**
 * @author mohen
 * @Description: com.mohen.captcha-demo.service
 * @create 2020-04-16-16:01
 */
public interface CaptchaService {
    /**
     * 验证码
     * @param ticket
     * @param randStr
     * @return
     */
    BaseResult captcha(String ticket, String randStr);
}
