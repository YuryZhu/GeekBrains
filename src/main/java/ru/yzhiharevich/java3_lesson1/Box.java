package ru.yzhiharevich.java3_lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {

    List<T> box = new ArrayList<>();
    int weightOfBox = 0;

    public List<T> getBox() {
        return box;
    }

    public Box(T t) {
        box.add(t);
    }

    public int getWeightOfBox() {
        Fruit fruit = null;
        for (int i = 0; i < box.size(); i++) {
            fruit = (Fruit) box.get(i);
            weightOfBox += (int) (fruit.getWEIGHT() * fruit.getAmount());
        }
        System.out.println("Вес коробки c " + fruit.getName() + " равен " + weightOfBox);
        return weightOfBox;
    }

    public boolean compare(int extBoxWeight) {
        return (extBoxWeight == weightOfBox);
    }

    public void doRearrangement(Box<T> boxTop) {
        List<T> boxTo = boxTop.getBox();
        Fruit fruitFrom = (Fruit) box.get(0);
        Fruit fruiTo = (Fruit) boxTo.get(0);
        if (fruitFrom.getName().equals(fruiTo.getName())) {
            boxTo.addAll(box);
            System.out.println("Я переложил " + fruitFrom.getName());
        }
    }
}

