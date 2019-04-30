package com.huanleichen.itoken.web.admin.service;

import com.huanleichen.itoken.web.admin.service.fallback.WebAdminHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-admin", fallback = WebAdminHystrix.class)
public interface WebAdminService {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    String login(@RequestParam(value = "loginCode") String loginCode, @RequestParam(value = "password") String password);
}
