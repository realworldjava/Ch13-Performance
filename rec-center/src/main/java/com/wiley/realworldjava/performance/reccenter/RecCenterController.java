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

@Controller
public class RecCenterController {

    private Map<String, Boolean> itemAvailability;
    private Map<String, String> idToName;

    private List<String> numberOfEmails;

    public RecCenterController() {
        idToName = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("tableTennis", "Table Tennis Rackets"),
                new AbstractMap.SimpleEntry<>("pickleball", "Pickleball Paddles"),
                new AbstractMap.SimpleEntry<>("basketball", "Basketball"),
                new AbstractMap.SimpleEntry<>("volleyball", "Volleyball"));

        itemAvailability = new HashMap<>();
        itemAvailability.put("pickleball", Boolean.TRUE);
        itemAvailability.put("tableTennis", Boolean.TRUE);
        itemAvailability.put("basketball", Boolean.TRUE);
        itemAvailability.put("volleyball", Boolean.TRUE);
    }

    @GetMapping("/status")
    public String status(Model model) {
        return sendToStatusPage(model);
    }

    private String sendToStatusPage(Model model) {
        model.addAttribute("idToName", idToName);
        model.addAttribute("itemAvailability", itemAvailability);
        return "status";
    }

    @PostMapping("/borrowItem")
    public String borrowItem(@RequestParam(name="itemId", required=true) String itemId, Model model) {
        if (itemAvailability.getOrDefault(itemId, Boolean.FALSE)) {
            itemAvailability.put(itemId, Boolean.FALSE);
            sleep(1_000);
        } else {
            model.addAttribute("message", "Sorry, item not available");
            // if not available, wait five seconds
            sleep(5_000);
        }
        return sendToStatusPage(model);
    }

    private void sleep(int numMillis) {
        try {
            Thread.sleep(numMillis);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @PostMapping("/returnItem")
    public String returnItem(@RequestParam(name="itemId", required=true) String itemId, Model model) {
        if (itemAvailability.getOrDefault(itemId, Boolean.FALSE)) {
            model.addAttribute("message", "Sorry, item not checkedout");
        } else {
            itemAvailability.put(itemId, Boolean.TRUE);

        }
        return sendToStatusPage(model);
    }

    @GetMapping("/emailSummary")
    public String emailSummary(Model model) {
        readFile();
        Map<Integer, Long> byMonth = calculateTotals();
        model.addAttribute("byMonth", byMonth);
        return "emailSummary";
    }

    private Map<Integer, Long> calculateTotals() {
        return numberOfEmails.stream()
                .map(Integer::parseInt)
                        .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }

    private void readFile() {
        Path path = Path.of("src/main/resources/lastYear.txt");
        List<String> fileData;
        try {
            fileData = Files.readAllLines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        if (numberOfEmails == null || numberOfEmailsChangedSinceLastRead(numberOfEmails, fileData)) {
            numberOfEmails = fileData;
        }
    }

    private boolean numberOfEmailsChangedSinceLastRead(List<String> numberOfEmails, List<String> fileData) {
        if (numberOfEmails.size() != fileData.size()) {
            return false;
        }
        for (int i = 0; i < numberOfEmails.size(); i++) {
            for (int j = 0; j < fileData.size(); j++) {
                if (i == j && ! numberOfEmails.get(i).equals(fileData.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
