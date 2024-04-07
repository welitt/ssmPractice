package com.ssm.config;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.jar.JarFile;

//public class CustomOoxmlClassLoader extends SecureClassLoader {
public class CustomOoxmlClassLoader extends ClassLoader {

    private final String poiOoxmlLiteJarPath;

    public CustomOoxmlClassLoader(ClassLoader parent, String poiOoxmlLiteJarPath) {
        super(parent);
        this.poiOoxmlLiteJarPath = poiOoxmlLiteJarPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.equals("org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument")) {
            byte[] classData = new byte[0];
            try {
                classData = loadClassData(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (classData != null) {
                return defineClass(name, classData, 0, classData.length);
            }
        }
        return super.findClass(name);
    }

    private byte[] loadClassData(String className) throws Exception {
        try (JarFile jar = new JarFile(poiOoxmlLiteJarPath)) {
            String entryName = className.replace(".", "/") + ".class";
            InputStream is = jar.getInputStream(jar.getEntry(entryName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                baos.write(ch);
            }
            return baos.toByteArray();
        }
    }

//    @Override
//    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        if (name.startsWith("org.apache.poi.xwpf.usermodel.")) {
//            // 当加载Apache POI相关的类时，优先使用自定义类加载器
//            return findClass(name);
//        }
//        return super.loadClass(name, resolve);
//    }


    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (!name.equals("org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument")) {
                    c = this.getParent().loadClass(name);
                } else {
                    c = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void setCustomClassLoaderIfNecessary(String poiOoxmlLiteJarPath) {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        if (!(currentClassLoader instanceof CustomOoxmlClassLoader)) {
            CustomOoxmlClassLoader customClassLoader = new CustomOoxmlClassLoader(currentClassLoader, poiOoxmlLiteJarPath);
            Thread.currentThread().setContextClassLoader(customClassLoader);
        }
    }
}
