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
     * 在初始化BasicServiceImpl时：
     * specificInterceptors中会出现InstantiationModelAwarePointcutAdvisorImpl 值为：logAdvicePointcut()
     * 和
     * specificInterceptors中会出现InstantiationModelAwarePointcutAdvisorImpl 值为：logAdvicePointcut2()
     * 最后的输出结果为：
     * ------------BasicService.class------------
     * ------------BasicServiceImpl.basicInfo() start------------
     * 表示先代理实现的父类，然后才是子类。
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
