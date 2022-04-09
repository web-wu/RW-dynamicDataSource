package com.tabwu.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 16:07
 * @Description:
 */
@Component
@Aspect
public class DynamicDBAspect {


    @Pointcut("@annotation(com.tabwu.config.WU)")
    public void aspect() {}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        WU annotation = method.getDeclaredAnnotation(WU.class);

        //若方法上没有wu注解，则默认使用主库master
        if (annotation == null) {
            DynamicDataSource.setDataSource("master");
        } else {
            //否则使用注解指定的数据源类型
            DynamicDataSource.setDataSource(annotation.value());
        }
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            //方法执行完后恢复为默认数据源类型,同时删除threadlocal的value值，防止内存泄漏
            DynamicDataSource.clearDataSource();
            return proceed;
        }

    }
}
