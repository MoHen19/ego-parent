package com.ego.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.rpc.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 搜索Controller
 *
 * @author zhoubin
 * @create 2020/1/2
 * @since 1.0.0
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference(interfaceClass = SearchService.class)
    private SearchService searchService;

    /**
     * 跳转前台搜索页
     * @param searchStr
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(String searchStr, Model model, HttpServletRequest request){
        try {
            //对输入的内容进行编码,防止中文乱码
            searchStr = URLEncoder.encode(searchStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:"
                + request.getSession().getServletContext().getAttribute("portalUrl")
                +"/search/index"
                +"?searchStr="+searchStr;
    }
}