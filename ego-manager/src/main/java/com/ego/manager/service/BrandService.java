package com.ego.manager.service;

import com.ego.manager.pojo.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MH
 * @site 品牌Service
 * @company
 * @create 2020-03-19-15:36
 */
@Service
public interface BrandService {
    /**
     * 查询所有商品顶级分类
     * @return
     */
    List<Brand> selectBrandList();
}