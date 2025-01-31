package org.example;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

final class ScrapingComparison {

    private static final int N_ATTEMPTS = 100;
    private static final String RESULTS_FILE = "results.csv";

    private static void saveResultsToCsv(List<Long> timesSelenium, List<Long> timesJsoup) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_FILE, true))) {
            writer.write("Attempt number,Time Selenium,Time Jsoup");
            writer.newLine();
            for (int i = 0; i < N_ATTEMPTS; i++) {
                writer.write((i+1) + "," + timesSelenium.get(i) + "," + timesJsoup.get(i));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error while writing results to file" + e);
        }

    }

    public static void main(String[] args) {
        var timesSelenium = ScraperPerformanceChecker.checkScraperPerformance(new SeleniumScraper(new ChromeDriver()), N_ATTEMPTS);
        var timesJsoup = ScraperPerformanceChecker.checkScraperPerformance(new JSoupScraper(), N_ATTEMPTS);

        saveResultsToCsv(timesSelenium, timesJsoup);
    }

}