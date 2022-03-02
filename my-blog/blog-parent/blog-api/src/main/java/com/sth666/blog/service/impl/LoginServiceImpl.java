package com.sth666.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sth666.blog.dao.mapper.SysUserMapper;
import com.sth666.blog.dao.pojo.SysUser;
import com.sth666.blog.service.LoginService;
import com.sth666.blog.service.SysUserService;
import com.sth666.blog.utils.JWTUtils;
import com.sth666.blog.vo.ErrorCode;
import com.sth666.blog.vo.Result;
import com.sth666.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-02 0:20
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String salt = "2522235302sth666.vip";

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 根据用户名和密码去User表钟查询是否存在
         * 3. 如果不存在 登录失败
         * 4. 如果存在 使用jwt生成token 返回给前端
         * 5. token 放入redis当中 redis 存储 token:user 信息的对应关系 设置过期时间
         *  (登录认证的时候,先认证token字符串是否合法, 去redis认证是否存在)
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + salt);
        SysUser sysUser = sysUserService.findUserBy(account, password);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
