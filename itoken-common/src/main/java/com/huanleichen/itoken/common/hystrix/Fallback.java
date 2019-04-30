package com.huanleichen.itoken.common.hystrix;

import com.google.common.collect.Lists;
import com.huanleichen.itoken.common.contants.HttpStatusContants;
import com.huanleichen.itoken.common.dto.BaseResult;
import com.huanleichen.itoken.common.utils.MapperUtils;

public class Fallback {
    public static String badGateway() {
        try {
            return MapperUtils.obj2json(BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error(
                            String.valueOf(HttpStatusContants.BAD_GATEWAY.getStatus()),
                            HttpStatusContants.BAD_GATEWAY.getContent())
            )));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
