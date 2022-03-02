package com.sth666.blog.service;

import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.LoginParam;

public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);
}
