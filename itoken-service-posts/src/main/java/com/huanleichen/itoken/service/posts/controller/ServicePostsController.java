package com.huanleichen.itoken.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.huanleichen.itoken.common.domain.TbPostsPost;
import com.huanleichen.itoken.common.dto.BaseResult;
import com.huanleichen.itoken.common.utils.MapperUtils;
import com.huanleichen.itoken.service.posts.service.PostsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/posts")
public class ServicePostsController {

    @Autowired
    private PostsService postsService;

    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public BaseResult get(
          @PathVariable(value = "postGuid") String postGuid
    ) {
        //设置查询条件
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        //获取对象
        TbPostsPost obj = postsService.selectOne(tbPostsPost);
        //将对象封装成所需格式并返回
        return BaseResult.ok(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String tbPostsPostJson,
            @RequestParam(required = true) String optBy
    ) {
        TbPostsPost tbPostsPost = null;
        int reslut = 0;
        try {
             tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //新增文章
        if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
            reslut = postsService.insert(tbPostsPost, optBy);
        }

        //更新文章
        else {
            reslut = postsService.update(tbPostsPost, optBy);
        }

        //操作失败
        if (reslut == 0) {
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("文章", "文章操作失败")));
        }
        //操作成功
        else {
            return BaseResult.ok("文章保存成功");
        }
    }

    @ApiOperation(value = "管理员分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbSysUserJson", value = "管理员对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "pageSize") int pageSize,
            @RequestParam(required = false) String tbPostsPostJson
    ) {
        TbPostsPost tbPostsPost =  null;
        if (!StringUtils.isBlank(tbPostsPostJson)) {
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //获取数据
        PageInfo<TbPostsPost> pageInfo = postsService.page(pageNum, pageSize, tbPostsPost);
        //封装所需对象
        BaseResult baseResult = BaseResult.ok(pageInfo.getList());

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageNum);
        cursor.setLimit(pageSize);

        baseResult.setCursor(cursor);

        return baseResult;
    }
}
