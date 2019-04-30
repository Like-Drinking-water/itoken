package com.huanleichen.itoken.service.admin.service;

import com.huanleichen.itoken.common.domain.TbSysUser;
import com.huanleichen.itoken.common.service.service.BaseService;

public interface AdminService extends BaseService<TbSysUser> {
    /**
     * 注册
     * @param tbSysUser
     */
    void register(TbSysUser tbSysUser);

    /**
     * 登录
     * @param loginCode
     * @param plantPassword
     * @return
     */
    TbSysUser login(String loginCode, String plantPassword);
}
