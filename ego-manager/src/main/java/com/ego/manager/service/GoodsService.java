package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.Goods;
import org.springframework.stereotype.Service;

/**
 * @author MH
 * @site 商品Service
 * @company
 * @create 2020-03-19-16:29
 */
@Service
public interface GoodsService {
    /**
     * 商品新增-保存
     * @param goods
     * @return
     */
    BaseResult save(Goods goods);

    /**
     * 商品列表-分页搜索
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    BaseResult search(Goods goods,Integer pageNum,Integer pageSize);
}
