package ru.yzhiharevich.java3_lesson3;

import java.io.IOException;

public class MainFIles {
    public static void main(String[] args) throws IOException {
        new FileReaderOwn().readFile();
        new FileSewMaster().sewFiles();
        new BookReader().readConsole();
    }
}
