package com.cyx.test;

import com.cyx.impl.UserDaoImpl;
import com.cyx.jdk.CglibProxy;
import com.cyx.jdk.JdkProxy;
import com.cyx.jdk.UserDao;
import com.cyx.jdk.UserService;

public class CglibTest {
    public static void main(String[] args) {
        //        创建代理对象
        CglibProxy cglibProxy = new CglibProxy();
//        创建目标对象
        UserService userDao = new UserService();
//        从代理对象中获取增强后的目标对象
        UserService userDao1 = (UserService) cglibProxy.createProxy(userDao);
//        执行方法
        userDao1.addUser();
        userDao1.deleteUser();
    }
}
