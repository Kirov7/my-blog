package com.sth666.blog.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sth666.blog.admin.pojo.Admin;
import com.sth666.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-15 23:58
 */

public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from permission where id in (select permission_id from admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long adminId);
}
