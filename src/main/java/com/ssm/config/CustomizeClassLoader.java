package com.ssm.config;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarFile;

/**
 * 自定义的类加载器
 */
public class CustomizeClassLoader extends ClassLoader{

    private String classPath;

    public CustomizeClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 重写findClass方法
     *
     * 如果不会写, 可以参考URLClassLoader中是如何加载AppClassLoader和ExtClassLoader的
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadBytes(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private byte[] loadBytes(String name) throws Exception {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            name = name.replace(".", "\\");
            // 如果path是jar包的路径，那么上述方法不能加载成功，抛出异常后执行此处的方法。
            JarFile jar = new JarFile(this.classPath);
            is = jar.getInputStream(jar.getEntry(name.replace("\\", "/") + ".class"));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if(is != null)
                    is.close();
                if(baos != null)
                    baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                /**
                 * 直接执行findClass()...什么意思呢? 首先会使用自定义类加载器加载类, 不在向上委托, 直接由
                 * 自己执行
                 *
                 * jvm自带的类还是需要由引导类加载器自动加载
                 */
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

    public static void main(String[] args) throws Exception {
        // 获取所有TestUse得到路径
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        Enumeration<URL> urls = classloader.getResources("org/openxmlformats/schemas/wordprocessingml/x2006/main/DocumentDocument.class");
//        Enumeration<URL> urls = classloader.getResources("com/duxiu/readfile/utils/ReadFileUtils.class");
        Enumeration<URL> urls = classloader.getResources("org/apache/poi/xwpf/usermodel/XWPFDocument.class");
//        "jar:file:/F:/ideaPros/ssmPractice/target/ssmPractice/WEB-INF/lib/poi-ooxml-5.2.3.jar!/org/apache/poi/xwpf/usermodel/XWPFDocument.class"
        // 循环加载两个jar包下的
        while (urls.hasMoreElements()) {
            URL url = (URL) urls.nextElement();
            String fullPath = url.getPath();
            System.out.println(fullPath);
            String[] strs = fullPath.split("!");
            // 获取文件的路径以及文件名
            String path = strs[0].replace("file:/","");
            String fileName = strs[1].substring(1).replace(".class", "").replace("/", ".");
            CustomizeClassLoader loader = new CustomizeClassLoader(path);
            // 加载类
            Class<?> clazz = loader.loadClass(fileName);
            // 反射创建实体
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("方法名", null);
            // 执行
            method.invoke(obj, null);
        }
    }

}