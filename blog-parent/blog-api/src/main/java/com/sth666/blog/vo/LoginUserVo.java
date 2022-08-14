package com.sth666.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-03 0:39
 */

@Data
public class LoginUserVo {

//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String account;

    private String nickName;

    private String avatar;
}
