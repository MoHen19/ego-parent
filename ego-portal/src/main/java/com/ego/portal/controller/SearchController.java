package com.ego.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.SearchService;
import com.ego.rpc.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 搜索Controller
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference(interfaceClass = SearchService.class)
    private SearchService searchService;

    /**
     * 跳转搜索页
     * @param searchStr
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(String searchStr, Model model){
        model.addAttribute("searchStr",searchStr);
        return "search/doSearch";
    }

    /**
     * 搜索
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("searchGoods")
    @ResponseBody
    public EgoPageInfo<GoodsVo> searchGoods(String searchStr,Integer pageNum,Integer pageSize){
        return searchService.doSearch(searchStr,pageNum,pageSize);
    }

}