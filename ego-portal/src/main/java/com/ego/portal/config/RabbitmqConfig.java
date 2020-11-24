package com.ego.portal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mohen
 * @Description: com.ego.amqp-provider
 *      RabbitMQ配置类
 * @create 2020-04-18-16:56
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 短信队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue("smsQueue");
    }

    /**
     * topic交换机(主题模式)
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("smsExchange");
    }

    /**
     * 将短信队列绑定到交换机
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with("*.sms");
    }

}
