package com.ego.portal.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.rpc.service.SmsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 短信队列消费者
 *
 * @author zhoubin
 * @create 2019/12/20
 * @since 1.0.0
 */
@Component
//监听具体的队列
@RabbitListener(queues = "smsQueue")
public class SmsConsumer {

   @Reference(interfaceClass = SmsService.class)
   private SmsService smsService;

   //处理队列里的消息
   @RabbitHandler
   public void process(String phoneNum){
      System.out.println("接受rabbitmq发送的消息："+phoneNum);
      //发送短信
      smsService.sendSms(phoneNum);
   }
}
