package com.sth666.blog.service;

import com.sth666.blog.vo.CategoryVo;
import com.sth666.blog.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long id);

    Result findAll();
}