package ru.yzhiharevich.marathon_lesson1;

import java.util.Random;

public class Randomiser {
    public int randomiser(int i) {
        Random randNumber = new Random();
        return randNumber.nextInt(i);
    }
}
