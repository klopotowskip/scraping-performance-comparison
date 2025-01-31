package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScrapingTest {
    private static Scraper seleniumScraper;
    private static Scraper jSoupScraper;

    @BeforeAll
    static void setUp() {
        seleniumScraper = new SeleniumScraper(new ChromeDriver());
        jSoupScraper = new JSoupScraper();
    }

    @Test
    void testEqualityOfTwoScrapedQuotesLists() {
        List<Quote> seleniumQuotes = seleniumScraper.scrapQuotes();
        List<Quote> jsoupQuotes = jSoupScraper.scrapQuotes();

        assertFalse(seleniumQuotes.isEmpty());
        assertFalse(jsoupQuotes.isEmpty());
        assertEquals(seleniumQuotes, jsoupQuotes);
    }
}
