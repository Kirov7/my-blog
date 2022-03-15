package com.sth666.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-15 11:51
 */

@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
