package ru.yzhiharevich.java3_lesson6;

public class MainTesting {
    public static int NUMBER = 4;

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 3, 3, 5, 6, 9};
        int[] arr1 = new int[]{4, 1, 4, 1, 1, 1, 4};
        getSymbolsAfterFour(arr);
        checkArrayWithOneAndFour(arr1);
    }

    public static int[] getSymbolsAfterFour(int[] fullArray) {
        int index = 0;
        for (int i = 0; i < fullArray.length; i++) {
            if (fullArray[i] == NUMBER) {
                index = i + 1;
            }
        }
        if (index == 0) {
            throw new RuntimeException("Числа  " + NUMBER + " нет в массиве");
        }

        int[] newArray = new int[fullArray.length - index];
        System.arraycopy(fullArray, index, newArray, 0, newArray.length);
        return newArray;
    }

    public static boolean checkArrayWithOneAndFour(int[] arr) {
        int fours = 0;
        int ones = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                fours += 1;
            } else if (arr[i] == 1) {
                ones += 1;
            } else if (arr[i] != 4 && arr[i] != 1) {
                return false;
            }
        }
        if (fours == ones) {
            return true;
        } else {
            return false;
        }
    }
}
