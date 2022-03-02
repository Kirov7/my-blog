package com.sth666.blog.handlee;

import com.sth666.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-02-28 23:22
 */

//对加了@Controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理,处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据
    public Result doException(Exception e){
        e.printStackTrace();
        return Result.fail(-999, "系统异常");
    }
}
