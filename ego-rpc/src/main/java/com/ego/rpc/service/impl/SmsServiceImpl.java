package com.ego.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.result.BaseResult;
import com.ego.rpc.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Component;

/**
 * @author mohen
 * @Description: com.ego.rpc.service.impl
 * @create 2020-04-16-19:25
 */
@Service(interfaceClass = SmsService.class)
@Component
public class SmsServiceImpl implements SmsService {

    /**
     * 发送短信
     * @param phoneNum
     * @return
     */
    @Override
    public BaseResult sendSms(String phoneNum) {
        try{

            Credential cred = new Credential("AKIDmhvufBtUEjhW5ONBiz4ylJaaNuDGDsSn", "DAvWvGFzThhSyGLL6fw6GBsGZsvlIkZ7");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"PhoneNumberSet\":[\"86"+phoneNum+"\"],\"TemplateID\":\"565820\\t\",\"Sign\":\"墨痕19\",\"SmsSdkAppid\":\"1400342349\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsRequest.toJsonString(resp));
            if ("Ok".equalsIgnoreCase(resp.getSendStatusSet()[0].getCode())){
                return BaseResult.success();
            }else{
                return BaseResult.error();
            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return BaseResult.error();
    }
}
