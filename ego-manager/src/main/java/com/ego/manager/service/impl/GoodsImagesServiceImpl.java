package com.ego.manager.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.manager.mapper.GoodsImagesMapper;
import com.ego.manager.pojo.GoodsImages;
import com.ego.manager.service.GoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-20-9:56
 */
@Service
public class GoodsImagesServiceImpl implements GoodsImagesService {

   @Autowired
   private GoodsImagesMapper goodsImagesMapper;

    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    @Override
    public BaseResult save(GoodsImages goodsImages) {
        int result = goodsImagesMapper.insertSelective(goodsImages);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }
}
