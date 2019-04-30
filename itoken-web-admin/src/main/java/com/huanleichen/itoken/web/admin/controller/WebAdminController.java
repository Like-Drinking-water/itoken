package com.huanleichen.itoken.web.admin.controller;

import com.huanleichen.itoken.web.admin.service.WebAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebAdminController {
    @Autowired
    private WebAdminService webAdminService;

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
        String result = webAdminService.login("", "");
        System.out.println(result);
        return "index";
    }
}
