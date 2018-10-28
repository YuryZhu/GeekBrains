package ru.yzhiharevich.letterreader_additional_lesson1;

public class MainReader {

    public static void main(String[] args) {

        String str = "rp  cvpilk jgqdsiavlwewjsadtijrtezjhvel pfwuu tybrrevnnnpxj\n" +
                "bnl izicllxvhazpvyw wujlqebpnauvpni n uyrou uovx  miwup";
        int k = str.length();
        String[] arr = new String[k];
        char[] mass = {'a', 'e', 'y', 'u', 'i', 'o'};
        int index = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < mass.length; j++)
                if (mass[j] == str.charAt(i)) {
                    index++;
                }
        System.out.print(index + " ");
        index = 0;
    }
}