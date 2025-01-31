package org.example;

import java.util.ArrayList;
import java.util.List;

final class ScraperPerformanceChecker {
    private ScraperPerformanceChecker() {
    }

    static List<Long> checkScraperPerformance(Scraper scraper, int nTries) {
        List<Long> timesMillis = new ArrayList<>();

        for (int i = 0; i < nTries; i++) {
            long start = System.currentTimeMillis();
            scraper.scrapQuotes();
            timesMillis.add(System.currentTimeMillis() - start);
        }

        return timesMillis;
    }
}
