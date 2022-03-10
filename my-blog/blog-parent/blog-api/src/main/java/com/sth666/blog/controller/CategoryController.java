package com.sth666.blog.controller;

import com.sth666.blog.service.CategoryService;
import com.sth666.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-10 22:39
 */

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // /categorys
    @GetMapping
    public Result categories(){
        return categoryService.findAll();
    }
}
