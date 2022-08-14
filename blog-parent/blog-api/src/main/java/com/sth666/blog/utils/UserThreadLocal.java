package com.sth666.blog.utils;

import com.sth666.blog.dao.pojo.SysUser;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-06 23:39
 */

public class UserThreadLocal {
    private UserThreadLocal(){}

    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
