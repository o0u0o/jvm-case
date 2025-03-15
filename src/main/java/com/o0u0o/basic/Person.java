package com.o0u0o.basic;

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
        System.out.println("Hello World!");
    }
}
