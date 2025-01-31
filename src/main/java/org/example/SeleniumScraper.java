package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

final class SeleniumScraper implements Scraper {

    private static final int MAX_PAGES_TO_SCRAP = 20;

    private final WebDriver driver;

    SeleniumScraper(final WebDriver driver) {
        this.driver = driver;
    }

    private Quote getQuote(WebElement element) {
        final String text = element.findElement(By.cssSelector(".text")).getText().replaceAll("[“”]", "");
        final String author = element.findElement(By.cssSelector(".author")).getText();
        final List<String> tags = element.findElements(By.cssSelector(".tag")).stream().map(WebElement::getText).toList();

        return new Quote(text, author, tags);
    }

    public List<Quote> scrapQuotes() {
        driver.get("https://quotes.toscrape.com/");

        int pagesScraped = 0;
        List<Quote> quotes = new ArrayList<>();

        while (pagesScraped < MAX_PAGES_TO_SCRAP) {
            quotes.addAll(driver.findElements(By.cssSelector(".quote")).stream().map(this::getQuote).toList());

            List<WebElement> nextPageButtons = driver.findElements(By.cssSelector(".pager .next a"));
            if (nextPageButtons.isEmpty()) {
                break;
            }
            nextPageButtons.getFirst().click();
            pagesScraped++;
        }

        return quotes;
    }
}
