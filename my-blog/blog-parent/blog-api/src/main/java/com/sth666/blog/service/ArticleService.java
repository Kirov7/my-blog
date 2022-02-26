package com.sth666.blog.service;

import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.PageParams;

public interface ArticleService {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);
}
