package com.ego.manager.controller;

import com.ego.common.result.BaseResult;
import com.ego.common.result.FileResult;
import com.ego.manager.pojo.Goods;
import com.ego.manager.pojo.GoodsImages;
import com.ego.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-19-14:43
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private GoodsImagesService goodsImagesService;
    /**
     * 商品-列表-页面跳转
     * @return
     */
    @RequestMapping("list")
    public String list(Model model){
        //查询所有分类
        model.addAttribute("gcList",goodsCategoryService.selectList());
        // 查询所有品牌
        model.addAttribute("brandList",brandService.selectBrandList());
        return "goods/goods-list";
    }

    /**
     * 商品-新增-页面跳转
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model){
        // 查询所有顶级分类
        model.addAttribute("gcList",goodsCategoryService.selectTopList());
        // 查询所有品牌
        model.addAttribute("brandList",brandService.selectBrandList());
        return "goods/goods-add";
    }

    /**
     * 商品-新增-保存
     * @param goods
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(Goods goods){
        return goodsService.save(goods);
    }

    /**
     * MultipartFile file
     *  Bootstrap 上传多个文件相当于多次调用,其他工具需要声明数组接收
     * 商品相册上传
     * @param file
     * @param goodsId
     * @return
     */
    @RequestMapping("images/save")
    @ResponseBody
    public BaseResult imagesSave(MultipartFile file,Integer goodsId) throws IOException {
        //System.out.println(file.getName());
        //System.out.println(file.getOriginalFilename());
        String filename = file.getOriginalFilename();
        //格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        filename = date+System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
        FileResult result= uploadService.upload(file.getInputStream(),filename);
        // 上传成功
        if (!StringUtils.isEmpty(result.getFileUrl())){
            GoodsImages goodsImages = new GoodsImages();
            // 设置上传成功后的url
            goodsImages.setImageUrl(result.getFileUrl());
            // 设置商品id
            goodsImages.setGoodsId(goodsId);
            return goodsImagesService.save(goodsImages);
        }else{
            // 上传失败
            return BaseResult.error();
        }
    }

    /**
     * 商品列表-分页查询
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public BaseResult search(Goods goods,Integer pageNum,Integer pageSize){
        return goodsService.search(goods,pageNum,pageSize);
    }
}
