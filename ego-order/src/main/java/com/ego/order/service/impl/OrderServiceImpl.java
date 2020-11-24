package com.ego.order.service.impl;

import com.ego.common.enums.*;
import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.order.mapper.OrderGoodsMapper;
import com.ego.order.mapper.OrderMapper;
import com.ego.order.pojo.Order;
import com.ego.order.pojo.OrderExample;
import com.ego.order.pojo.OrderGoods;
import com.ego.order.service.OrderService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MH
 * @site 订单实现类
 * @company
 * @create 2020-03-28-12:40
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Value("${redis.order.increment}")
    private String orderIncrement;

    /**
     * 保存订单
     *
     * @param cartResult
     * @param admin
     * @return
     */
    @Override
    public BaseResult saveOrder(CartResult cartResult, Admin admin) {
        //1.保存订单表
        Order order = new Order();
        //订单编号 ego_年月日时分秒_自增key
        String orderSn =
                "ego" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + getIncrement(orderIncrement);
        order.setOrderSn(orderSn);
        //用户id
        order.setUserId(admin.getAdminId().intValue());
        //订单状态
        order.setOrderStatus(OrderStatus.no_confirm.getStatus());
        //发货状态
        order.setShippingStatus(ShipStatus.no_send.getStatus());
        //支付状态
        order.setPayStatus(PayStatus.no_pay.getStatus());
        //商品总价
        order.setGoodsPrice(cartResult.getTotalPrice());
        //订单总价
        order.setTotalAmount(cartResult.getTotalPrice());
        //设置应付金额
        order.setOrderAmount(cartResult.getTotalPrice());
        //下单时间
        Long addTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        order.setAddTime(addTime.intValue());
        int result = orderMapper.insertSelective(order);
        List<CartVo> cartList = cartResult.getCartList();
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        //2.保存订单商品表
        if (result > 0) {
            for (CartVo cartVo : cartList) {
                //order_id, goods_id, goods_name, goods_price, goods_num, prom_type, is_send
                OrderGoods orderGoods = new OrderGoods();
                //订单id
                orderGoods.setOrderId(order.getOrderId());
                //商品id
                orderGoods.setGoodsId(cartVo.getGoodsId());
                //商品名称
                orderGoods.setGoodsName(cartVo.getGoodsName());
                //商品价格
                orderGoods.setGoodsPrice(cartVo.getMarketPrice());
                //商品数量
                orderGoods.setGoodsNum(cartVo.getGoodsNum().shortValue());
                //订单方式
                orderGoods.setPromType(PromTypeStatus.normal.getStatus());
                //是否发货
                orderGoods.setIsSend(SendStatus.no_pay.getStatus());
                orderGoodsList.add(orderGoods);
            }
            result = orderGoodsMapper.insertOrderGoodsList(orderGoodsList);
            if (result == orderGoodsList.size()) {
                BaseResult baseResult = BaseResult.success();
                //订单编号
                baseResult.setMessage(orderSn);
                return baseResult;
            }
        }
        return BaseResult.error();
    }

    /**
     * 通过订单编号查询订单
     * @param orderSn
     * @return
     */
    @Override
    public Order selectOrderByOrderSn(String orderSn) {
        // 创建查询对象
        OrderExample example = new OrderExample();
        // 创建查询条件
        example.createCriteria().andOrderSnEqualTo(orderSn);
        // 查询
        List<Order> orders = orderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orders)){
            return null;
        }
        return orders.get(0);
    }


    /**
     * redis自增key
     */
    private Long getIncrement(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }
}