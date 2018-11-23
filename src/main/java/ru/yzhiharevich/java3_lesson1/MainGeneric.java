package ru.yzhiharevich.java3_lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGeneric {
    public static void main(String[] args) {

        firstExercise();
        secondExercise();

        Box<Apple> boxA = new Box<>(new Apple(18, 1.0f, "Яблоко"));
        Box<Apple> boxA2 = new Box<>(new Apple(10, 1.0f, "Яблоко"));
        Box<Apple> boxA3 = new Box<>(new Apple(20, 1.0f, "Яблоко"));
        Box<Orange> boxOr = new Box<>(new Orange(2, 1.5f, "Апельсин"));
        Box<Orange> boxOr2 = new Box<>(new Orange(8, 1.5f, "Апельсин"));

        int weightOfBoxA = boxA.getWeightOfBox();
        int weightOfBoxA2 = boxA2.getWeightOfBox();
        int weightOfBoxA23 = boxA3.getWeightOfBox();
        int weightOfBoxb = boxOr.getWeightOfBox();
        int weightOfBoxb2 = boxOr2.getWeightOfBox();

        //перекладываем
        boxA.doRearrangement(boxA3);
        System.out.println("Равна ли коробка А - Б? " + boxOr.compare(weightOfBoxA));
    }

    public static void firstExercise() {
        Object[] obj = new Object[]{"Привет", 10, 3.4};
        Object tempObj = obj[0];
        obj[0] = obj[1];
        obj[1] = tempObj;
        for (Object s : obj) {
            System.out.println(s);
        }
    }

    public static void secondExercise() {
        String[] arr = {"Привет", "Как", "Дела"};
        List<String> arrL = new ArrayList<>(Arrays.asList(arr));
        arrL.remove(2);
        arrL.add("Не удаляй меня");
        System.out.println(arrL);
    }
}
