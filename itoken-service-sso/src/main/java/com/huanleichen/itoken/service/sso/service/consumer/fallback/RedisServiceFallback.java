package com.huanleichen.itoken.service.sso.service.consumer.fallback;

import com.huanleichen.itoken.common.hystrix.Fallback;
import com.huanleichen.itoken.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }
}
