package com.wiley.realworldjava.performance.reccenter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GenerateLastYearFile {

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        Path path = Path.of("src/main/resources/lastYear.txt");
        for (int i= 0; i< 50_000; i++) {
            int month = random.nextInt(1,13);
            list.add(month + "");
            list.add(6 + "");
        }
        Collections.shuffle(list);
        Files.write(path, list);
    }
}
