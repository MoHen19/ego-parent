package com.ego.portal.service;

import com.ego.common.pojo.AdminWithBLOBs;
import com.ego.common.result.BaseResult;

/**
 * @author MH
 * @site 用户注册Service
 * @company
 * @create 2020-03-28-20:34
 */
public interface UserService {
    /**
     * 注册用户
     * @param admin
     * @return
     */
    BaseResult saveUser(AdminWithBLOBs admin);
}
