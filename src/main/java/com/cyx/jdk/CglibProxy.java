package com.cyx.jdk;

import com.cyx.aspect.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

//    代理方法
    public Object createProxy(Object target) {
//        创建一个动态类对象
        Enhancer enhancer = new Enhancer();
//        确定需要增强的类，设置其父类
        enhancer.setSuperclass(target.getClass());
//        添加回调函数
        enhancer.setCallback(this);
//        返回创建的代理类
        return enhancer.create();
    }
    /*
    * proxy GGlib 根据指定父类生成的代理对象
    * method 拦截方法
    * objects 拦截方法的参数数组
    * methodProxy 方法的代理对象，用于执行父类的方法
    * */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //        声明切面
        MyAspect myAspect = new MyAspect();
//        前增强
        myAspect.check_Permissions();
//        在目标类上调用方法，并传入参数
        Object obj = methodProxy.invokeSuper(o,objects);
//        后增强
        myAspect.log();
        return obj;
    }
}
