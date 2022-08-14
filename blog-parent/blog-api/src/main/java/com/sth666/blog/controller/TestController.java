package com.sth666.blog.controller;

import com.sth666.blog.dao.pojo.SysUser;
import com.sth666.blog.utils.UserThreadLocal;
import com.sth666.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
