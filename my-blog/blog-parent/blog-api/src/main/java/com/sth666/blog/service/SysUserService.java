package com.sth666.blog.service;

import com.sth666.blog.dao.pojo.SysUser;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUserBy(String account, String password);
}
