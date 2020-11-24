package com.ego.sso.service;

import com.ego.common.pojo.Admin;

/**
 * 单点登录系统service
 */
public interface SSOService {
	/**
	 * 验证用户信息返回ticket
	 *
	 * @param admin
	 * @return
	 */
	String login(Admin admin);


	/**
	 * 验证票据信息返回用户信息
	 *
	 * @param ticket
	 * @return
	 */
	Admin validate(String ticket);

	/**
	 * 用户退出
	 *
	 * @param ticket
	 */
	void logout(String ticket);
}
