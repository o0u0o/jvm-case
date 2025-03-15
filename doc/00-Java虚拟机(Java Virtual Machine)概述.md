# 00-Java虚拟机(Java Virtual Machine)概述

## 1、什么是Java虚拟机
> Write Once Run Anywhere  这是Java平台的口号

![Java跨平台](https://images.techgeng.com/blog/Snipaste_2025-03-15_22-43-45.png)

## 2、JVM到底该怎么学？
JVM到底从那边开始学起？
![JVM](https://images.techgeng.com/blog/Snipaste_2025-03-15_22-45-28.png)
- （1）源码到类文件
- （2）类文件到JVM
- （3）JVM各种折腾[内部结构、执行方式、垃圾回收、本地调用等]

### 2.1 源码到类文件
一下是一个源码案例：

#### 2.1.1 源码Demo
```java

/**
 * <h1>演示类加载</h1>
 *
 * @author o0u0o
 * @since 2025/3/15 19:44
 */
public class Person {
    private String name = "o0u0o";
    private int age;
    private final double salary = 100;
    private static String address;
    private final static String hobby = "Programming";
    private static Object obj = new Object();

    public void say(){
        System.out.println("Person say...");
    }

    public static int calc(int op1, int op2){
        op1 = 3;
        int result = op1 + op2;
        Object obj = new Object();
        return result;
    }

    public static void main(String[] args) {
        calc(1, 2);
        System.out.println("Hello World!");
    }
}

```

#### 2.1.2 前期编译
下面是编译过程（大学编译原理课程）：
![编译原理](https://images.techgeng.com/blog/Snipaste_2025-03-15_22-51-59.png)
> Person.java -> 词法分析器 -> tokens流 -> 语法分析器 -> 语法树/抽象语法树 -> 语义分析器 -> 注解抽象语法树 -> 字节码生成器 -> Person.class文件

