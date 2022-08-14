package com.sth666.blog.controller;

import com.sth666.blog.service.CommentsService;
import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-08 0:12
 */

@RestController
@RequestMapping("comments")
public class CommentsController {

    // /comments/article/{id}

    @Autowired
    private CommentsService commentsService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id){
        return commentsService.commentsByArticleId(id);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentsService.comment(commentParam);
    }
}
