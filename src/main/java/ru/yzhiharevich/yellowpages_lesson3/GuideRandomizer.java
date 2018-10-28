package ru.yzhiharevich.yellowpages_lesson3;

import java.util.LinkedList;
import java.util.Random;

public class GuideRandomizer {
    public static String lastRandom() {
        Random random = new Random();
        String characters = "олипрнтaх";
        int lastNameLength = 5;
        char[] text = new char[lastNameLength];
        for (int i = 0; i < lastNameLength; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        String lastName = new String(text).substring(0, 1).toUpperCase() + new String(text).substring(1) + "ов";
        return lastName;
    }
    public static String PhoneRandom() {
        Random random = new Random();
        String characters = "олипрнтaх";
        int lastNameLength = 5;
        char[] text = new char[lastNameLength];
        for (int i = 0; i < lastNameLength; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        String lastName = new String(text).substring(0, 1).toUpperCase() + new String(text).substring(1) + "ов";
        return lastName;
    }
}
