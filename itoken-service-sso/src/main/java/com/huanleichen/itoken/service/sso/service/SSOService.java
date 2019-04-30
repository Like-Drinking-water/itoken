package com.huanleichen.itoken.service.sso.service;

import com.huanleichen.itoken.common.domain.TbSysUser;

public interface SSOService {
    TbSysUser login(String loginCode, String plantPassword);
}
