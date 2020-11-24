package com.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 *
 * @author zhoubin
 * @create 2019/12/12
 * @since 1.0.0
 */
@Controller
public class PageController {
	/**
	 * 公共页面跳转 restful风格
	 * 比如：前台传welcome就跳转至/WEB-INF/pages/welcome1.ftl
	 * 比如：前台传login就跳转至/WEB-INF/pages/login.ftl
	 * ...
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		System.out.println(page);
		return page;
	}

	@RequestMapping("/")
	public String page() {
		System.out.println("index");
		return "index";
	}
}