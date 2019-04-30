package com.huanleichen.itoken.service.posts.service.impl;

import com.huanleichen.itoken.common.domain.TbPostsPost;
import com.huanleichen.itoken.common.service.service.impl.BaseServiceImpl;
import com.huanleichen.itoken.service.posts.mapper.TbPostsPostExtendMapper;
import com.huanleichen.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostExtendMapper> implements PostsService {
}
