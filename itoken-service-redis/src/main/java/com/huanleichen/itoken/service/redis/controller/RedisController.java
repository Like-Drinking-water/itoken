package com.huanleichen.itoken.service.redis.controller;

import com.huanleichen.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(String key, String value, long seconds) {
        redisService.put(key, value, seconds);
        return "Ok";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(String key) {
        String value = redisService.get(key);
        return value;
    }
}
