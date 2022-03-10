package com.sth666.blog.service;

import com.sth666.blog.dao.pojo.SysUser;
import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.UserVo;

public interface SysUserService {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);

    SysUser findUserBy(String account, String password);

    //根据token查询用户信息
    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
