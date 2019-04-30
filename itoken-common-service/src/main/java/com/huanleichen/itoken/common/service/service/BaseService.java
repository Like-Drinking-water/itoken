package com.huanleichen.itoken.common.service.service;

import com.github.pagehelper.PageInfo;
import com.huanleichen.itoken.common.domain.BaseDomain;

public interface BaseService<T extends BaseDomain> {
    int insert(T t, String createBy);

    int update(T t, String updateBy);

    int delete(T t);

    T selectOne(T t);

    int count(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);
}
