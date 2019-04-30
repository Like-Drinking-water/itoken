package com.huanleichen.itoken.service.sso.controller;

import com.google.common.collect.Lists;
import com.huanleichen.itoken.common.domain.TbSysUser;
import com.huanleichen.itoken.common.dto.BaseResult;
import com.huanleichen.itoken.common.utils.CookieUtils;
import com.huanleichen.itoken.common.utils.MapperUtils;
import com.huanleichen.itoken.service.sso.service.SSOService;
import com.huanleichen.itoken.service.sso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class SSOController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private SSOService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(@RequestParam(value = "loginCode")String loginCode,
                        @RequestParam(value = "password")String password,
                        HttpServletRequest request, HttpServletResponse response) {
        TbSysUser tbSysUser = service.login(loginCode, password);

        //登录成功
        if (tbSysUser != null) {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if ("Ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token);
                return BaseResult.ok(tbSysUser);
            } else {
                return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("服务器", "服务器连接错误，请稍后再试")));
            }

        }

        //登录失败
        else {
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("登录", "用户名或密码错误")));
        }

    }
}
