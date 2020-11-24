package com.ego.rpc.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车结果对象
 *
 * @author zhoubin
 * @create 2019/12/19
 * @since 1.0.0
 */
@Setter
@Getter
@ToString
public class CartResult implements Serializable {
   private static final long serialVersionUID = -8936811376383018291L;
   /**
    * 购物车列表
    */
   private List<CartVo> cartList;

   /**
    * 购物车总金额
    */
   private BigDecimal totalPrice;
}
