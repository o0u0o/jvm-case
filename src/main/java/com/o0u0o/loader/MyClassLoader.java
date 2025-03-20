package com.o0u0o.loader;

import java.io.*;

/**
 * <h1>自定义类加载器</h1>
 *
 * @author o0u0o
 * @since 2025/3/20 23:23
 */
public class MyClassLoader extends ClassLoader {

    private String root;

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        else {
            //此方法负责将二进制的字节码转换为Class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }


    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }
    public void setRoot(String root) {
        this.root = root;
    }

}
