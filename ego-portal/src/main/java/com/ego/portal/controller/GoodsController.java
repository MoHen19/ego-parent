package com.ego.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.rpc.service.GoodsCategoryService;
import com.ego.rpc.vo.GoodsCategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-24-20:57
 */
@Controller
@RequestMapping("goodsCategory")
public class GoodsController {

    @Reference(interfaceClass = GoodsCategoryService.class)
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping("list")
    //@GetMapping
    @ResponseBody
    public List<GoodsCategoryVo> goodsCategoryVoList(){
        return goodsCategoryService.selectAllList();
    }
}
