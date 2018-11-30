package ru.yzhiharevich.java3_lesson3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileSewMaster {
    public void sewFiles() throws IOException {
        List<Path> inputs = Arrays.asList(
                Paths.get("123/1.txt"),
                Paths.get("123/2.txt"),
                Paths.get("123/3.txt"),
                Paths.get("123/4.txt"),
                Paths.get("123/5.txt")
        );

        Path output = Paths.get("123/summary.txt");
        Charset charset = StandardCharsets.UTF_8;

        for (Path path : inputs) {
            List<String> lines = Files.readAllLines(path, charset);
            Files.write(output, lines, charset, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
    }
}
