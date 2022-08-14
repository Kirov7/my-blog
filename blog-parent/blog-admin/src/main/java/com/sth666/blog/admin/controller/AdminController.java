package com.sth666.blog.admin.controller;

import com.sth666.blog.admin.model.params.PageParam;
import com.sth666.blog.admin.pojo.Permission;
import com.sth666.blog.admin.service.PermissionService;
import com.sth666.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-15 11:34
 */

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/search/{key}")
    public Result search(@RequestBody PageParam pageParam,@PathVariable("key") Long key, Permission permission){
        return permissionService.search(permission, key, pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }
}
