package com.sth666.blog.service;

import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.CommentParam;

public interface CommentsService {
    /**
     * 根据文章ID查询 查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);


    Result comment(CommentParam commentParam);
}
