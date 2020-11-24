package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.vo.GoodsCategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-16-17:13
 */
@Service
public interface GoodsCategoryService {
    /**
     * 商品分类-新增分类-查询所有顶级分类
     * @return
     */
    List<GoodsCategory> selectTopList();

    /**
     * 商品分类-新增分类-根据父id查询子分类
     * @param parentId
     * @return
     */
    List<GoodsCategory> selectListByParentId(Short parentId);

    /**
     * 商品分类-添加-保存
     * @param goodsCategory
     * @return
     */
    BaseResult save(GoodsCategory goodsCategory);

    /**
     * 商品分类-列表查询
     * @return
     */
    List<GoodsCategoryVo> selectAllList();

    /**
     * 查询所有商品分类
     * @return
     */
    List<GoodsCategory> selectList();
}
