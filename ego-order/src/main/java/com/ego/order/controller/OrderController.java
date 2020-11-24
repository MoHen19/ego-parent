package com.ego.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.order.config.AlipayConfig;
import com.ego.order.pojo.Order;
import com.ego.order.service.OrderService;
import com.ego.rpc.service.CartService;
import com.ego.rpc.vo.CartResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-28-10:07
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Reference(interfaceClass = CartService.class)
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     * 跳转到预订单页面
     *
     * @return
     */
    @RequestMapping("preOrder")
    public String preOrder(Model model, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        model.addAttribute("cartResult", cartService.getCartList(admin));
        return "order/preOrder";
    }

    /**
     * 订单提交成功页面
     *
     * @return
     */
    @RequestMapping("submitOrder")
    public String submitOrder(Model model, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        CartResult cartResult = cartService.getCartList(admin);
        //存入订单信息
        BaseResult baseResult = orderService.saveOrder(cartResult, admin);
        //清除购物车信息
        cartService.clearCart(admin);
        //总价
        model.addAttribute("totalPrice", cartResult.getTotalPrice());
        //订单编号
        model.addAttribute("orderSn", baseResult.getMessage());
        //跳转页面
        return "order/submitOrder";
    }

    /**
     * 去付款
     * @param request
     * @param model
     * @param orderSn
     * @return
     */
    @RequestMapping("payment")
    public String payment(HttpServletRequest request,Model model,String orderSn) {
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            Order order = orderService.selectOrderByOrderSn(orderSn);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderSn;
            //付款金额，必填
            String total_amount = String.valueOf(order.getOrderAmount());
            //订单名称，必填
            String subject = "用户为"+order.getUserId()+"的订单";
            //商品描述，可空
            String body = "";

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            model.addAttribute("result",result);
            return "order/payment";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到我的订单页面
     * @return
     */
    @RequestMapping("myOrder")
    public String myOrder() {
    /**
     * 这边要处理对应订单对象里的状态
     */
        return "order/myOrder";
    }

}
