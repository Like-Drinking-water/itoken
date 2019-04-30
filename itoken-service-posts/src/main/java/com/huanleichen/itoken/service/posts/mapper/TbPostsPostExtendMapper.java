package com.huanleichen.itoken.service.posts.mapper;

import com.huanleichen.itoken.common.domain.TbPostsPost;
import com.huanleichen.itoken.common.service.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}