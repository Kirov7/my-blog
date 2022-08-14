package com.sth666.blog.controller;

import com.sth666.blog.service.LoginService;
import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-02 0:11
 */

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录 验证用户 访问用户表
        return loginService.login(loginParam);
    }
}
