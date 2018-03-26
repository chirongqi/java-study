package com.chirq.classloader;

import java.lang.reflect.Method;

public class ClassLoaderTest {

    public void testA() {
        ClassLoader loader = this.getClass().getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }
    }

    public static void main(String[] args) throws Exception {
        // java.net.URL[] urls =
        // sun.misc.Launcher.getBootstrapClassPath().getURLs();
        // for (int i = 0; i < urls.length; i++) {
        // System.out.println(urls[i].toExternalForm());
        // }

        System.out.println(System.getProperty("sun.boot.class.path"));

        new ClassLoaderTest().testA();

        Class c1 = Class.forName("com.chirq.classloader.ClassLoaderTest");
        Object o1 = c1.newInstance();
        Method m1 = c1.getMethod("testA");
        m1.invoke(o1);
    }
}
