package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.portal.service.CaptchaService;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.stereotype.Service;

/**
 * @author mohen
 * @Description: com.mohen.captcha-demo.service.impl
 * @create 2020-04-16-16:05
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    /**
     *
     * @param ticket
     * @param randStr
     * @return
     */
    @Override
    public BaseResult captcha(String ticket, String randStr) {
        try{

            Credential cred = new Credential("AKIDmhvufBtUEjhW5ONBiz4ylJaaNuDGDsSn",
                    "DAvWvGFzThhSyGLL6fw6GBsGZsvlIkZ7");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("captcha.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CaptchaClient client = new CaptchaClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"CaptchaType\":9,\"Ticket\":\""+ticket+"\",\"UserIp\":\"127.0.0.1\"," +
                    "\"Randstr\":\""+randStr+"\",\"CaptchaAppId\":2009946866,\"AppSecretKey\":\"0mzQkrXn1-wYp8EYM-hJgqg**\"}";
            DescribeCaptchaResultRequest req = DescribeCaptchaResultRequest.fromJsonString(params, DescribeCaptchaResultRequest.class);

            DescribeCaptchaResultResponse resp = client.DescribeCaptchaResult(req);

            System.out.println(DescribeCaptchaResultRequest.toJsonString(resp));
            if ("OK".equals(resp.getCaptchaMsg())){
                return BaseResult.success();
            }else {
                return BaseResult.error();
            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return BaseResult.error();
    }
}
