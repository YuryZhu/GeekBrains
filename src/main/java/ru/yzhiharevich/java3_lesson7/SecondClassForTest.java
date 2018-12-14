package ru.yzhiharevich.java3_lesson7;

public class SecondClassForTest {

    @MyTest
    public static void start(Class classes) {
        System.out.println(classes.getName());
    }
    @MyTest
    public static void doSome1(Class classes) {
        System.out.println(classes.getName());
    }
    @MyTest
    public static void doSome2(Class classes) {
        System.out.println(classes.getName());
    }
}
