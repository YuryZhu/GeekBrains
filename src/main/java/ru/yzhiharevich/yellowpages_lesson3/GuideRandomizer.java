package ru.yzhiharevich.yellowpages_lesson3;

import java.util.LinkedList;
import java.util.Random;

public class GuideRandomizer {
    public LinkedList getGuide(int rowAmaount){
        LinkedList<LastNames> ll = new LinkedList();
        for (int i = 0; i < rowAmaount; i++) {
            String lastN = lastRandomize();
            String phone = phoneRandomize();
            ll.add(new LastNames(lastN, phone));
        }
        return ll;
    }
    public static String lastRandomize() {
        Random random = new Random();
        String characters = "aс";
        int lastNameLength = 4;
        char[] text = new char[lastNameLength];
        for (int i = 0; i < lastNameLength; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        String lastName = new String(text).substring(0, 1).toUpperCase() + new String(text).substring(1) + "ов";
        return lastName;
    }
    public static String phoneRandomize() {
        Random random = new Random();
        String characters = "0123456789";
        int lastNameLength = 10;
        char[] text = new char[lastNameLength];
        for (int i = 0; i < lastNameLength; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        String lastName = "7" + new String(text);
        return lastName;
    }
}
