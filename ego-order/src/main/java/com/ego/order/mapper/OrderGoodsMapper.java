package com.ego.order.mapper;

import com.ego.order.pojo.OrderGoods;
import com.ego.order.pojo.OrderGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderGoodsMapper {
    long countByExample(OrderGoodsExample example);

    int deleteByExample(OrderGoodsExample example);

    int deleteByPrimaryKey(Integer recId);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsExample example);

    OrderGoods selectByPrimaryKey(Integer recId);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    /**
     * 批量添加订单商品表
     *  order_id, goods_id, goods_name, goods_price, goods_num, prom_type, is_send
     * @param orderGoodsList
     * @return
     */
    int insertOrderGoodsList(@Param("orderGoodsList") List<OrderGoods> orderGoodsList);
}