package com.huanleichen.itoken.service.admin.service.impl;

import com.huanleichen.itoken.common.domain.TbSysUser;
import com.huanleichen.itoken.common.service.service.impl.BaseServiceImpl;
import com.huanleichen.itoken.service.admin.mapper.TbSysUserExtendMapper;
import com.huanleichen.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserExtendMapper> implements AdminService {

    @Autowired
    private TbSysUserExtendMapper mapper;

    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
        mapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode", loginCode);

        TbSysUser tbSysUser = mapper.selectOneByExample(example);
        if (tbSysUser != null) {
           if (!tbSysUser.getPassword().equals(DigestUtils.md5DigestAsHex(plantPassword.getBytes()))) {
               tbSysUser = null;
           }
        }
        return tbSysUser;
    }
}
