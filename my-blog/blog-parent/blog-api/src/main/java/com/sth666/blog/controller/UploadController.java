package com.sth666.blog.controller;

import com.sth666.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-03-11 11:04
 */

@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {
        //原始文件名称 比如aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件 不建议上传到服务器上
        return null;
    }
}
