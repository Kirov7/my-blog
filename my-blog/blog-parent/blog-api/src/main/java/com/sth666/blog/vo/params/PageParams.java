package com.sth666.blog.vo.params;

import lombok.Data;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-02-26 22:23
 */

@Data
public class PageParams {
    //当前页数
    private int page = 1;
    //每页显示的数量
    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;
}
