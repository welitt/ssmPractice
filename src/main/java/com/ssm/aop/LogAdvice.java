package com.ssm.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop切面类
 */
@Aspect
@Component
public class LogAdvice {
    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.ssm.service.BasicService.*(..))")
    public void logAdvicePointcut(){}

    @Before("logAdvicePointcut()")
    public void logAdvice(){
        System.out.println("------------BasicService.class------------");
    }


    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.ssm.service.impl.BasicServiceImpl.basicInfo())")
    public void logAdvicePointcut2(){}

    @Before("logAdvicePointcut2()")
    public void logAdvice2(){
        System.out.println("------------BasicServiceImpl.basicInfo() start------------");
    }


}
