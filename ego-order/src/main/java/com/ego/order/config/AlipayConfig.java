package com.ego.order.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 类名：com.alipay.config.AlipayConfig
 * 功能：基础配置类
 * 详细：设置帐户有关信息及返回路径
 * 修改日期：2017-04-05
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 *
 * @author MH19
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static String app_id ="2016102200741363";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static String merchant_private_key =
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCM2DlUxVC+TQ3pIe9PUpV8Du67lzTXEpW3pxXZCzaF9Wg/M50TGYW5av14QtljoP0W0UYIA0oPrx+McuPdZ1I78nKtHFTfd/FGEj7NwWtHs7d5zYk4W4RcKa0KByEB2nFS/WEhiIC0IWNxbBERpm84EadyLllRMuduf+dvoqSoy31lghn4gHLTZt18SIop7apOoUOL4pwmJFU0zDFaDHakq+KAR6V53HNVyNCdY68XaVe4H+S4CND65l9V35a3uFdBnO3aPvnHnFScravUhl5o2kC+giNmJEHze++6t41colKkpay6tt5UDrt4AvAvsuWbRwBKxQIHFfWg1FdxI8HvAgMBAAECggEAVNxm1rO4baDBs+kVZobMuJ3Kq850MO3qtWf/2vFNnbL6Z7IyfvKOxuQ26oHkt4mlVF0tQ+Cve/m5GYeT0a/SNXpkK/d5D9gV0HFn09MpT19yOfpkJkfF3T3jG7ql4Zb7VtSB9JFpcp73aryBIFBWjMLeUAarHCh+lu6LxJCLg4OG/mowgPNPuTbWY8bQXf/+X90YLez4DO3giFlbegBnnk5DOnJSDNnLxsw3pDLgOtIflBbXKDXsAVBDkBoUele1JGyVdXeNZcN9VhLYMInV1ccMzQQZmxW51lPE1gGQlAh9NGAsckDgsbF7OqTyC5Ghzzi6aK2OqPQsw4BoKCX3eQKBgQDl1Tm09GEeNinbsbqkZUdKGDnkH9QBtdEJjIu/Ap5h2/rpAgW4DT3/bythiS0hrIGZvhVdrZUE9lnghcjjwQ7KJvVmwDfdC8CzImYx4gDhwI59RDw8CdXXjWszaMJFvtS0pof2NySt8KykMxaPh2f00kpB6ggLJbo9gwh2PmWN4wKBgQCc4VIooz1MOLuhw+8FRwe2G9A5cZYRmyxG51O1jnQHWKTUBBk5GC726I2wokMP5j0DKDEiDxtR7p7+Gb76dPjlMJUJ33V+w2/9jdttMUjcUZj7CAIcqEYPMYWjf2VZoROlhTw/7Y4wcwhHxns+AGuUrGtG9/opwA/ZSUjESUi5hQKBgGKx+PDzDI17tufn331fHIj99EY5QMJnQFf4ab5hsn/Vhl7vBmOuFBSPubJNxPqz6IeL8ALlWpvh6mCXMthS/DXlGva/zn124Y5LVFVRdw16sfWrCq7Bjnnc+c7+yoZG13KoHLSno3Gqa4irg6GpBQRGFatOg5u93MczcLvOOa//AoGABSRNcQ+InsrtO2wUlXOyiQF4XamfZYxUcy55y+WTX/pDzL8lpH1qbKVKn5Nx5z+AmG3kzlz4ZEMR6volDEhxL/HbsKsmSrVVIlZX7shCvxXdkby9dPr+8NiKrMUsKTfiUPaA9wduNaBPkSqsN2gEHqdYDMdr55pn6pNAU06jl5ECgYBe9NZw+/f1xsXL75vTNvIdjQyRE01cggO1EhFAq44JDfuVQcy9WgjDrfMQPcEYDZq2D0pGsqLFPAmTp85tfJ9CBQDf8VE5gb1td2+gbEnZtID3Xq/Y8lpG/+dPtAtA4vahBZ1CBMlIJUs5dJKcXGQ9YQ2WF9p7hkj4EzCJ3Bjbyw==";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    public static String alipay_public_key =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhBIxLpIf7gwc021+c9ENR76D4HliGVJdV4aCzjzRy25NVIx5KGvgFNpvJxZshKUDm75BEoZR8G1bDyjwonpMSZHoo7ByDr+1NqrsZRY9PENypIpjNlkrBr93N3BRh47kwMGXMePfRuCD0/PSpbSBCLcfNAwgXcB3nRTW4tVyxdIDK9DvL1jJ4v0FL+aKMqrSkSwjwyU9uSNBeMu43hBk53QDnBZuIacwQSCX6kmqSJKUXE1KQQ1PzlNB3BRNw+AsUjVI0efWuh8qlhAauMmX9ReV9kMzxITA0xO8DQJKqmI99juHAXzkqMJYJgwvCwLCB4bQkDSn78BzG0YFMczyhQIDAQAB";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://dptbcw.natappfree.cc/ego-portal/order/callback";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String return_url = "http://localhost:9092/ego-order/order/myOrder";

    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    public static String charset = "utf-8";

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

