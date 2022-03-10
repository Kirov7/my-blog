package com.sth666.blog.service;

import com.sth666.blog.dao.mapper.ArticleMapper;
import com.sth666.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-07 23:27
 */

@Component
public interface ThreadService {


    //期望此操作在线程池中执行 不会影响原有的主线程
    @Async("taskExecutor")
    void updateArticleViewCount(ArticleMapper articleMapper, Article article);

}
