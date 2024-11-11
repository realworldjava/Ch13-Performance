package com.wiley.realworldjava.performance.reccenter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmailSummaryPerformanceImprovement {

    private Map<String, Long> emailSummary = getEmailSummary();

   // @GetMapping("/emailSummary")
    public String emailSummary(Model model) {
        model.addAttribute("byMonth", emailSummary);
        return "emailSummary";
    }

    private Map<String, Long> getEmailSummary() {
        Path path = Path.of("src/main/resources/lastYear.txt");
        List<String> fileData;
        try {
            fileData = Files.readAllLines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return fileData.stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }

}
