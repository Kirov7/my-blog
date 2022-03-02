package com.sth666.blog.service;

import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.TagVo;

import java.util.List;

public interface TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);
}
