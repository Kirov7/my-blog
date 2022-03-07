package com.sth666.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sth666.blog.dao.dos.Archives;
import com.sth666.blog.dao.mapper.ArticleBodyMapper;
import com.sth666.blog.dao.mapper.ArticleMapper;
import com.sth666.blog.dao.pojo.Article;
import com.sth666.blog.dao.pojo.ArticleBody;
import com.sth666.blog.service.ArticleService;
import com.sth666.blog.service.CategoryService;
import com.sth666.blog.service.SysUserService;
import com.sth666.blog.service.TagService;
import com.sth666.blog.vo.ArticleBodyVo;
import com.sth666.blog.vo.ArticleVo;
import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-02-26 22:37
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Result listArticle(PageParams pageParams) {
        /*
         * 1. 分页查询 article数据库表
         */
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //order by create_data desc
        //先按照置顶进行排序,再按照创建日期进行排序
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        //不能直接返回
        List<ArticleVo> articleVoList = copyList(records, true, true);
        return Result.success(articleVoList);
    }

    @Override
    public Result hotArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getAuthorId, Article::getTitle);
        queryWrapper.last("limit " + limit);
        //SELECT id, tittle FROM article ORDER BY view_count DESC LIMIT 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        //返回vo对象
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getAuthorId, Article::getTitle);
        queryWrapper.last("limit " + limit);
        //SELECT id, tittle FROM article ORDER BY creat_date DESC LIMIT 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        //返回vo对象
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1. 根据ID查询 文章信息
         * 2. 根据BodyId和categoryid 去做关联查询
         */
        Article article = this.articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true, true ,true);

        return Result.success(articleVo);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
//        records.forEach(record -> articleVoList.add(copy(record)));
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, false, false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag,
                                     boolean isAuthor, boolean isBody,
                                     boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
//        records.forEach(record -> articleVoList.add(copy(record)));
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, isBody, isCategory));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag,
                           boolean isAuthor, boolean isBody,
                           boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);

        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd"));
        //并不是所有的接口都需要标签,作者信息
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleByBodyId(bodyId));
        }
        if (isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    @Mapper
    private ArticleBodyVo findArticleByBodyId(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
