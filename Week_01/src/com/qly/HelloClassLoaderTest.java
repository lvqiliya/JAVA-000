package com.qly;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 加载 hello.xlass 并调用 hello() 方法
 *
 * @author qly
 */
public class HelloClassLoaderTest {

    static class HelloClassLoader extends ClassLoader {
        private final String path;

        public HelloClassLoader(String path) {
            this.path = path;
        }

        private byte[] loadFile(String name) throws IOException {
            FileInputStream fis = new FileInputStream(this.path + name + ".xlass");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadFile(name);
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) ((byte) 255 - data[i]);
                }
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader hcl = new HelloClassLoader("Week_01/resources/");
        Class clazz = hcl.loadClass("Hello");
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(obj);
    }
}
