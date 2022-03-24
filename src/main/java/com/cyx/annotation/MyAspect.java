package com.cyx.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    //定义切入点表达式
    @Pointcut("execution(* com.cyx.impl.*.*(..))")
    //使用一个返回值void、方法体为空的方法来命名切入点
    private void myPointCut(){}

//    前置通知
    @Before("myPointCut()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("前置通知..");
        System.out.print("目标类是："+joinPoint.getTarget());
        System.out.println(",被植入处理方法为："+joinPoint.getSignature().getName());
    }

    @AfterReturning("myPointCut()")
    public void myAfterReturning(JoinPoint joinPoint){
        System.out.print("后置通知");
        System.out.println("被植入增强处理的目标方法为："+joinPoint.getSignature().getName());
    }

    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕开始");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕结束");
        return obj;
    }

    @AfterThrowing(value = "myPointCut()",throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint,Throwable e) {
        System.out.println("异常通知"+e.getMessage());
    }

    @After("myPointCut()")
    public void myAfter() {
        System.out.println("最终通知");
    }


}
