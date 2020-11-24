package com.ego.order.service;

import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.order.pojo.Order;
import com.ego.rpc.vo.CartResult;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-28-12:25
 */
public interface OrderService {
    /**
     * 订单保存
     * @param cartResult
     * @param admin
     * @return
     */
    BaseResult saveOrder(CartResult cartResult, Admin admin);

    /**
     * 通过订单编号查询订单
     * @param orderSn
     * @return
     */
    Order selectOrderByOrderSn(String orderSn);
}
