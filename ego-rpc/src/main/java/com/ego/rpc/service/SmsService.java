package com.ego.rpc.service;

import com.ego.common.result.BaseResult;

/**
 * @author mohen
 * @Description: com.ego.rpc.service
 *      短信Service
 * @create 2020-04-16-19:24
 */
public interface SmsService {

    /**
     * 发送短信
     * @param phoneNum
     * @return
     */
    BaseResult sendSms(String phoneNum);
}
