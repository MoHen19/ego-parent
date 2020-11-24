package com.ego.portal.service.impl;

import com.ego.common.pojo.AdminWithBLOBs;
import com.ego.common.result.BaseResult;
import com.ego.common.util.Md5Util;
import com.ego.common.util.RandomUtil;
import com.ego.portal.mapper.AdminMapper;
import com.ego.portal.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author MH
 * @site 用户注册service实现类
 * @company
 * @create 2020-03-28-20:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 注册用户
     * @param admin
     * @return
     */
    @Override
    public BaseResult saveUser(AdminWithBLOBs admin) {
        //生成盐
        String salt = RandomUtil.getRandom1();
        //加密密码
        admin.setPassword(Md5Util.getMd5WithSalt(admin.getPassword(), salt));
        admin.setEcSalt(salt);
        //注册时间
        admin.setAddTime((int) LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        int result = adminMapper.insertSelective(admin);
        // 如果注册成功
        if (result>0){
            // 将准备发松德短信的信息写入消息队列
            rabbitTemplate.convertAndSend("smsExchange","registry.sms",admin.getEmail());
            System.out.println("rabbitmq发送的消息: " + admin.getEmail());
            return BaseResult.success();
        }
        return BaseResult.error();
    }
}
