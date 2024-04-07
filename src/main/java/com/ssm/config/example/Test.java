package com.ssm.config.example;

import java.net.URL;
import java.net.URLClassLoader;

public class Test {
    // 方法有问题：在web中运行报错java.lang.NoClassDefFoundError: org/apache/commons/io/output/UnsynchronizedByteArrayOutputStream
    public static void main(String[] args) throws Exception {
        // 创建一个URLClassLoader来加载libA的版本
        URL libAUrl = new URL("file:/path/to/libA.jar");
        URLClassLoader libAClassLoader = new URLClassLoader(new URL[]{libAUrl});

        // 创建一个URLClassLoader来加载libB的版本
        URL libBUrl = new URL("file:/path/to/libB.jar");
        URLClassLoader libBClassLoader = new URLClassLoader(new URL[]{libBUrl});

        // 使用对应的类加载器加载CommonUtils类
        Class<?> libAClass = libAClassLoader.loadClass("com.example.libA.CommonUtils");
        Class<?> libBClass = libBClassLoader.loadClass("com.example.libB.CommonUtils");

        // 创建实例并调用方法
        Object libAInstance = libAClass.newInstance();
        Object libBInstance = libBClass.newInstance();

        libAClass.getMethod("printMessage").invoke(libAInstance);
        libBClass.getMethod("printMessage").invoke(libBInstance);
    }

}
