package com.o0u0o.loader;

import com.o0u0o.basic.Worker;

/**
 * <h1>类加载器的演示</h1>
 * 演示类加载器
 * AppClassLoader -> ExtClassLoader -> BootstrapClassLoader
 *
 * 类加载器的历史演变：
 * 1、在1.2版本的JVM当中，只有一个类加载器，就是Bootstrap类加载器。也就是根类加载器。但是这样会出现一个问题：
 * 加入用户调用他编写的 java.lang.String 类，理论上该类可以访问和改变java.lang包下其他类的默认访问修饰符的属性
 * 和方法的能力。也就是说，我们其他类使用String时，也会调用这个类，因为只有一个类加载器，无法判断到底是加载哪一个.
 * @author o0u0o
 * @since 2025/3/18 17:13
 */
public class Demo3 {

    public static void main(String[] args) {
        System.out.println(Worker.class.getClassLoader());
        System.out.println(Worker.class.getClassLoader().getParent());
        // 这里应该是输出null 因为Bootstrap 是使用C C++编写的，所以这里看不到
        System.out.println(Worker.class.getClassLoader().getParent().getParent());
        System.out.println(String.class.getClassLoader());
    }
}
