package com.huanleichen.itoken.web.admin.service.fallback;

import com.google.common.collect.Lists;
import com.huanleichen.itoken.common.contants.HttpStatusContants;
import com.huanleichen.itoken.common.dto.BaseResult;
import com.huanleichen.itoken.common.hystrix.Fallback;
import com.huanleichen.itoken.common.utils.MapperUtils;
import com.huanleichen.itoken.web.admin.service.WebAdminService;
import org.springframework.stereotype.Component;


@Component
public class WebAdminHystrix implements WebAdminService {
    @Override
    public String login(String loginCode, String password) {
       return Fallback.badGateway();
    }
}
