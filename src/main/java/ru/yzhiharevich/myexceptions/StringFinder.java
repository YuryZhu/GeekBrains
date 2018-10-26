package ru.yzhiharevich.myexceptions;

public class StringFinder {
        public static void main(String[] args) {
        try {
            String[][] arr = new String[][]{
                    {"1", "9", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"13", "1", "1", "7"}};
             ArrayHandler.handlerOfArray(arr);
        } catch (MySizeArrayException e) {
            e.printStackTrace();
        }
    }
}

