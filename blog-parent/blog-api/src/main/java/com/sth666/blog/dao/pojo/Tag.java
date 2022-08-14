package com.sth666.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("tag")
@Data
public class Tag {

    private Long id;

    private String avatar;

    private String tagName;

}