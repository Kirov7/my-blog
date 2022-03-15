package com.sth666.blog.admin.model.params;

import lombok.Data;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-15 11:38
 */

@Data
public class PageParam {
    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
