package com.huanleichen.itoken.service.sso.service.impl;

import com.huanleichen.itoken.common.domain.TbSysUser;
import com.huanleichen.itoken.common.hystrix.Fallback;
import com.huanleichen.itoken.common.utils.MapperUtils;
import com.huanleichen.itoken.service.sso.mapper.TbSysUserMapper;
import com.huanleichen.itoken.service.sso.service.SSOService;
import com.huanleichen.itoken.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class SSOServiceImpl implements SSOService {
    private static final Logger logger = LoggerFactory.getLogger(SSOServiceImpl.class);

    @Autowired
    private TbSysUserMapper mapper;

    @Autowired
    private RedisService redisService;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        String json = redisService.get(loginCode);
        TbSysUser tbSysUser = null;

        //缓冲中有数据
        if (json != null) {
            try {
                if (!json.equals(MapperUtils.obj2json(Fallback.badGateway()))) {
                    tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                }
            } catch (Exception e) {
                logger.warn("触发熔断: {}", e.getMessage());
            }
        }

        //缓冲中没有数据
        else {

            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);

            tbSysUser = mapper.selectOneByExample(example);
            if (tbSysUser != null) {
                //密码不正确
                if (!tbSysUser.getPassword().equals(DigestUtils.md5DigestAsHex(plantPassword.getBytes()))) {
                    tbSysUser = null;
                }
                //密码正确
                else {
                    try {
                        redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return tbSysUser;
    }
}
