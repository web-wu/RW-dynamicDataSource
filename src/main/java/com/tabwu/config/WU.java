package com.tabwu.config;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 16:05
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface WU {
    String value() default "";
}
