package com.ego.rpc.service;

import com.ego.rpc.vo.GoodsCategoryVo;
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
     * 商品分类-列表查询
     * @return
     */
    List<GoodsCategoryVo> selectAllList();
}
