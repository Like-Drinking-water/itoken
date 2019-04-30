package com.huanleichen.itoken.service.admin.controller;

import com.google.common.collect.Lists;
import com.huanleichen.itoken.common.dto.BaseResult;
import com.huanleichen.itoken.common.domain.TbSysUser;
import com.huanleichen.itoken.service.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceAdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public BaseResult login(String loginCode, String password) {
        BaseResult baseResult = cheakLogin(loginCode, password);
        //登录验证失败
        if (baseResult != null) {
            return baseResult;
        }

        TbSysUser tbSysUser = adminService.login(loginCode, password);
        //登录成功
        if (tbSysUser != null) {
            return BaseResult.ok(tbSysUser);
        }
        //登录失败
        else {
            return BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("账号密码", "账号或密码错误")
            ));
        }
    }

    private BaseResult cheakLogin(String loginCode, String password) {
        //账号密码其中一个为空返回错误
        if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)) {
            return BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("账号", "账号不能为空"),
                    new BaseResult.Error("密码", "密码不能为空")
            ));
        } else {
            return null;
        }
    }
}
