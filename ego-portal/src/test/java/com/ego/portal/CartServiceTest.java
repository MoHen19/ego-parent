package com.ego.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.Admin;
import com.ego.rpc.service.CartService;
import com.ego.rpc.vo.CartVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-26-22:37
 */
@SpringBootTest
public class CartServiceTest {

    @Reference(interfaceClass = CartService.class)
    private CartService cartService;

    /**
     * 添加购物车
     */
    @Test
    public void testAddCart(){
        Admin admin = new Admin();
        admin.setAdminId((short) 1);
        CartVo cartVo = new CartVo();
        cartVo.setGoodsId(123);
        cartVo.setGoodsName("疯狂java讲义");
        cartVo.setMarketPrice(new BigDecimal("99"));
        cartVo.setAddTime(new Date());
        cartVo.setGoodsNum(5);
        cartService.addCart(cartVo,admin);
    }

    /**
     * 获取购物车数量
     */
    @Test
    public void testGetCartNum(){
        Admin admin = new Admin();
        admin.setAdminId((short) 1);
        Integer cartNum = cartService.getCartNum(admin);
        System.out.println(cartNum);
    }
}
