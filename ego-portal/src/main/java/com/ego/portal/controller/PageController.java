package com.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 * @author MH19
 */
@Controller
public class PageController {

	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/")
	public String page(){
		return "index";
	}

	/**
	 * 精确匹配登录
	 * @param redirectUrl
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public String login(String redirectUrl, Model model){
		model.addAttribute("redirectUrl",redirectUrl);
		return "login";
	}
}