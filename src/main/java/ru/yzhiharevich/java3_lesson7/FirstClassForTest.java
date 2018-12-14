package ru.yzhiharevich.java3_lesson7;

public class FirstClassForTest {

    public static void doSome0() {
        System.out.println();
    }


//        @BeforeSuite
//    public static void start2(Class classes) {
//        System.out.println(classes.getName() + "start2");
//    }
    @MyTest(priority = 2)
    public static void doSome2() {
        System.out.println("doSome2");
    }

    @MyTest (priority = 7)
    public static void doSome1() {
        System.out.println("doSome1");
    }

    @BeforeSuite
    public static void start() {
        System.out.println("start");
    }

    @MyTest (priority = 1)
    public static void doSome3() {
        System.out.println("doSome3");
    }

    @AfterSuite
    public static void close() {
        System.out.println("close");
    }
}
