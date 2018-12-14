package ru.yzhiharevich.java3_lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        runTests();
    }

    public static void runTests() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = FirstClassForTest.class.getDeclaredMethods();
        ArrayList<Method> arrLMeth = new ArrayList<>();
        for (Method o : methods) {
            if (o.isAnnotationPresent(MyTest.class)) {
                arrLMeth.add(o);
                System.out.println(o.toString());
            }
        }
        arrLMeth.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(MyTest.class).priority() - o1.getAnnotation(MyTest.class).priority();
            }
        });
        for (Method o : methods) {
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (arrLMeth.get(0).isAnnotationPresent(BeforeSuite.class))
                    throw new RuntimeException("Too many BeforeSuite");
                arrLMeth.add(0, o);
            }
            if (o.isAnnotationPresent(AfterSuite.class)) {
                arrLMeth.add(o);
            }
        }
        for (Method o : arrLMeth) {
            o.invoke(null);
        }
    }
}
