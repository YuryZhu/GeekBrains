package ru.yzhiharevich.java3_lesson3;

import java.io.*;

public class FileReaderOwn {
    public void readFile() {
        try (FileInputStream in = new FileInputStream("123/50byte.dz")) {
            byte[] arr = new byte[1];
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
