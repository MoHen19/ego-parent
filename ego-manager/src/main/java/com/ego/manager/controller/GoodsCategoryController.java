package com.ego.manager.controller;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author MH
 * @site 商品分类页面跳转
 * @company
 * @create 2020-03-15-17:20
 */
@Controller
@RequestMapping("category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 商品分类-列表-页面跳转
     * @return
     */
    @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("gcvList",goodsCategoryService.selectAllList());
        return "goods/category/category-list";
    }

    /**
     * 商品分类-添加-页面跳转
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model){
        //查询所有顶级分类
        model.addAttribute("gcList",goodsCategoryService.selectTopList());
        return "goods/category/category-add";
    }

    /**
     * 商品分类-添加-分类联查
     * @param parentId
     * @return
     */
    @RequestMapping("/{parentId}")
    @ResponseBody
    public List<GoodsCategory> selectCategoryList(@PathVariable Short parentId){
        return goodsCategoryService.selectListByParentId(parentId);
    }

    /**
     * 商品分类-添加-保存
     * @param goodsCategory
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(GoodsCategory goodsCategory){
        return goodsCategoryService.save(goodsCategory);
    }
}
