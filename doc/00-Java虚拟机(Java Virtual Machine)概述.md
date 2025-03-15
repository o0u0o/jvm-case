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


#### 2.1.3 类文件(Class文件)

![](https://images.techgeng.com/blog/Snipaste_2025-03-15_23-04-25.png)
`javap -v -p Person.class`进行反编译，查看字节码信息和指令等信息
~~~
o0u0o@8c85904e8f3c basic % javap -v -p Person.class
Classfile /Users/o0u0o/IdeaProjects/jvm-case/target/classes/com/o0u0o/basic/Person.class
  Last modified 2025-3-15; size 1142 bytes
  MD5 checksum e53d9af96faeda209561f1706ea80fd4
  Compiled from "Person.java"
public class com.o0u0o.basic.Person
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #10.#47        // java/lang/Object."<init>":()V
   #2 = String             #48            // o0u0o
   #3 = Fieldref           #14.#49        // com/o0u0o/basic/Person.name:Ljava/lang/String;
   #4 = Double             100.0d
   #6 = Fieldref           #14.#50        // com/o0u0o/basic/Person.salary:D
   #7 = Fieldref           #51.#52        // java/lang/System.out:Ljava/io/PrintStream;
   #8 = String             #53            // Person say...
   #9 = Methodref          #54.#55        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #10 = Class              #56            // java/lang/Object
  #11 = Methodref          #14.#57        // com/o0u0o/basic/Person.calc:(II)I
  #12 = String             #58            // Hello World!
  #13 = Fieldref           #14.#59        // com/o0u0o/basic/Person.obj:Ljava/lang/Object;
  #14 = Class              #60            // com/o0u0o/basic/Person
  #15 = Utf8               name
  #16 = Utf8               Ljava/lang/String;
  #17 = Utf8               age
  #18 = Utf8               I
  #19 = Utf8               salary
  #20 = Utf8               D
  #21 = Utf8               ConstantValue
  #22 = Utf8               address
  #23 = Utf8               hobby
  #24 = String             #61            // Programming
  #25 = Utf8               obj
  #26 = Utf8               Ljava/lang/Object;
  #27 = Utf8               <init>
  #28 = Utf8               ()V
  #29 = Utf8               Code
  #30 = Utf8               LineNumberTable
  #31 = Utf8               LocalVariableTable
  #32 = Utf8               this
  #33 = Utf8               Lcom/o0u0o/basic/Person;
  #34 = Utf8               say
  #35 = Utf8               calc
  #36 = Utf8               (II)I
  #37 = Utf8               op1
  #38 = Utf8               op2
  #39 = Utf8               result
  #40 = Utf8               main
  #41 = Utf8               ([Ljava/lang/String;)V
  #42 = Utf8               args
  #43 = Utf8               [Ljava/lang/String;
  #44 = Utf8               <clinit>
  #45 = Utf8               SourceFile
  #46 = Utf8               Person.java
  #47 = NameAndType        #27:#28        // "<init>":()V
  #48 = Utf8               o0u0o
  #49 = NameAndType        #15:#16        // name:Ljava/lang/String;
  #50 = NameAndType        #19:#20        // salary:D
  #51 = Class              #62            // java/lang/System
  #52 = NameAndType        #63:#64        // out:Ljava/io/PrintStream;
  #53 = Utf8               Person say...
  #54 = Class              #65            // java/io/PrintStream
  #55 = NameAndType        #66:#67        // println:(Ljava/lang/String;)V
  #56 = Utf8               java/lang/Object
  #57 = NameAndType        #35:#36        // calc:(II)I
  #58 = Utf8               Hello World!
  #59 = NameAndType        #25:#26        // obj:Ljava/lang/Object;
  #60 = Utf8               com/o0u0o/basic/Person
  #61 = Utf8               Programming
  #62 = Utf8               java/lang/System
  #63 = Utf8               out
  #64 = Utf8               Ljava/io/PrintStream;
  #65 = Utf8               java/io/PrintStream
  #66 = Utf8               println
  #67 = Utf8               (Ljava/lang/String;)V
{
  private java.lang.String name;
    descriptor: Ljava/lang/String;
    flags: ACC_PRIVATE

  private int age;
    descriptor: I
    flags: ACC_PRIVATE

  private final double salary;
    descriptor: D
    flags: ACC_PRIVATE, ACC_FINAL
    ConstantValue: double 100.0d

  private static java.lang.String address;
    descriptor: Ljava/lang/String;
    flags: ACC_PRIVATE, ACC_STATIC

  private static final java.lang.String hobby;
    descriptor: Ljava/lang/String;
    flags: ACC_PRIVATE, ACC_STATIC, ACC_FINAL
    ConstantValue: String Programming

  private static java.lang.Object obj;
    descriptor: Ljava/lang/Object;
    flags: ACC_PRIVATE, ACC_STATIC

  public com.o0u0o.basic.Person();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: ldc           #2                  // String o0u0o
         7: putfield      #3                  // Field name:Ljava/lang/String;
        10: aload_0
        11: ldc2_w        #4                  // double 100.0d
        14: putfield      #6                  // Field salary:D
        17: return
      LineNumberTable:
        line 9: 0
        line 10: 4
        line 12: 10
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      18     0  this   Lcom/o0u0o/basic/Person;

  public void say();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #8                  // String Person say...
         5: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 18: 0
        line 19: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/o0u0o/basic/Person;

  public static int calc(int, int);
    descriptor: (II)I
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=4, args_size=2
         0: iconst_3
         1: istore_0
         2: iload_0
         3: iload_1
         4: iadd
         5: istore_2
         6: new           #10                 // class java/lang/Object
         9: dup
        10: invokespecial #1                  // Method java/lang/Object."<init>":()V
        13: astore_3
        14: iload_2
        15: ireturn
      LineNumberTable:
        line 22: 0
        line 23: 2
        line 24: 6
        line 25: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      16     0   op1   I
            0      16     1   op2   I
            6      10     2 result   I
           14       2     3   obj   Ljava/lang/Object;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: iconst_1
         1: iconst_2
         2: invokestatic  #11                 // Method calc:(II)I
         5: pop
         6: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         9: ldc           #12                 // String Hello World!
        11: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        14: return
      LineNumberTable:
        line 29: 0
        line 30: 6
        line 31: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  args   [Ljava/lang/String;

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=2, locals=0, args_size=0
         0: new           #10                 // class java/lang/Object
         3: dup
         4: invokespecial #1                  // Method java/lang/Object."<init>":()V
         7: putstatic     #13                 // Field obj:Ljava/lang/Object;
        10: return
      LineNumberTable:
        line 15: 0
}
SourceFile: "Person.java"
~~~

##### 类文件结构(The ClassFile Structure)
官网: https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
~~~
ClassFile {
    u4 magic;
    u2 minor_version;
    u2 major_version;
    u2 constant_pool_count;
    cp_info constant_pool[constant_pool_count-1]; u2 access_flags;
    u2 this_class;
    u2 super_class;
    u2 interfaces_count;
    u2 interfaces[interfaces_count];
    u2 fields_count;
    field_info fields[fields_count];
    u2 methods_count;
    method_info methods[methods_count];
    u2                 attributes_count;
    attribute_info attributes[attributes_count];
}
~~~