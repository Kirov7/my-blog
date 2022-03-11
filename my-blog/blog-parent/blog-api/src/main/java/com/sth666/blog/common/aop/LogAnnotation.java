package com.sth666.blog.common.aop;

import java.lang.annotation.*;

//TYPE代表可以放在类上面 method代表可以放在方法上
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
}
