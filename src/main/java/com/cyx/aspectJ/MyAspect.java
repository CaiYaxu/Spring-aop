package com.cyx.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
//    前置通知
    public void myBefore(JoinPoint joinPoint) {
        System.out.print("前置通知");
        System.out.print(",目标类是"+joinPoint.getTarget());
        System.out.println(",被植入增强处理的目标方法为"+joinPoint.getSignature().getName());
    }

//    后置通知
    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.print("后置通知");
        System.out.println(",被植入增强处理的目标方法为"+joinPoint.getSignature().getName());
    }

    /*
    * 环绕通知
    * ProceedingJoinPoint是JoinPoint子接口，表示可以执行的目标方法
    * 1. 必须是Object类型的返回值
    * 2. 必须接收一个参数，类型为 ProceedingJoinPoint
    * 3. 必须 throws Throwable
    * */
    public Object myAround (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕开始：执行目标方法之前");
//        执行当前目标方法
        Object obj = proceedingJoinPoint.proceed();
//        结束
        System.out.println("环绕结束：执行目标方法之后");
        return obj;
    }

//    异常通知
    public void myAfterThrowing(JoinPoint joinPoint,Throwable e) {
        System.out.println("异常通知："+ e.getMessage());
    }

//    最终通知
    public void myAfter() {
        System.out.println("最终通知");
    }
}
