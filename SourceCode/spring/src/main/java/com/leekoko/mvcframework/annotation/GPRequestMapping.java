package com.leekoko.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * 创建注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})   //使用位置：类，方法
@Retention(RetentionPolicy.RUNTIME)  //生命周期
@Documented
public @interface GPRequestMapping {
    String value() default "";
}
