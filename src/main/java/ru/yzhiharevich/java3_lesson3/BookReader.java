package ru.yzhiharevich.java3_lesson3;

import java.io.*;
import java.util.Scanner;


public class BookReader {
    public void readConsole() {
        Scanner in = new Scanner(System.in);
        System.out.println("Напишите номер страницы");
        int page = Integer.parseInt(in.nextLine());
        readTheBook(page);
    }

    public void readTheBook(int page) {
        String FILENAME = "123/пушкин.txt";
        int skip = page * 1800;
        long t = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream(FILENAME)) {
            in.skip(skip);
            byte[] arr = new byte[1];
            int x;
            int count = 0;
            while ((x = in.read(arr)) > 0) {
                if (count == 1800) break;
                count++;
                System.out.print(new String(arr, 0, x, "Cp1251"));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n" + "\n" + (System.currentTimeMillis() - t));
    }
}
