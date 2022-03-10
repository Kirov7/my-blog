package com.sth666.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sth666.blog.dao.mapper.ArticleMapper;
import com.sth666.blog.dao.pojo.Article;
import com.sth666.blog.service.TagService;
import com.sth666.blog.service.ThreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-07 23:31
 */

@Service
public class ThreadServiceImpl implements ThreadService {
    //期望此操作在线程池中执行 不会影响原有的主线程
    @Override
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        //设置一个 为了在多线程的环境下线程安全
        updateWrapper.eq(Article::getViewCounts, viewCounts);
        // update article set view count = 100 where view_count = 99 and id = 11;
        articleMapper.update(articleUpdate, updateWrapper);
        try {
            Thread.sleep(5000);
            System.out.println("更新完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
