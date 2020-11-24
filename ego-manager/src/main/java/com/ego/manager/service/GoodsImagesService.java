package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsImages;

/**
 * @author MH
 * @site 商品相册
 * @company
 * @create 2020-03-20-9:54
 */
public interface GoodsImagesService {
    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    BaseResult save(GoodsImages goodsImages);
}
