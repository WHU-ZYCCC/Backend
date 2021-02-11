package com.example.demo.AOP;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author dzy
 * @title: MyAspect
 * @projectName demo
 * @description: TODO
 * @date 2021/2/1120:50
 */
@Configuration
@Aspect
public class MyAspect {
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void MyPoint() {
    }
    @Around(value = "MyPoint()")
    public Object toJsonStr(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(String.format("%s:%dms",
                joinPoint.getSignature(),end-begin));
        return JSON.toJSONString(result);
    }
}
