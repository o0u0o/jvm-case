package com.o0u0o.loader;

/**
 * <h1>类加载Demo</h1>
 * 类的加载过程：
 *  加载 --> 链接(link) --> 初始化 --> 使用 --> 卸载
 *             +- 验证(Verify)
 *             +- 准备(Prepare)
 *             +- 解析(Resolve)
 * @author o0u0o
 * @since 2025/3/15 19:37
 */
public class Demo01 {

    //这里没有赋值
    private static int i;
    private static String str;

    public static void main(String[] args) {
        //在JVM 链接中的准备阶段，会为i和str赋默认值
        System.out.println("i的默认值是：" + i);
        System.out.println("str的默认值是：" + str);
    }

}
