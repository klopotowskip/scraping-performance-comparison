package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class JSoupScraper implements Scraper {

    private static final int MAX_PAGES_TO_SCRAP = 20;


    Quote getQuote(Element element) {
        final String text = element.select(".text").text().replaceAll("[“”]", "");
        final String author = element.select(".author").text();
        final List<String> tags = element.select(".tag").stream().map(Element::text).toList();

        return new Quote(text, author, tags);
    }

    private Document get(String url) {
        try {
            return Jsoup.connect(url).userAgent("Chrome").get();
        } catch (IOException e) {
            throw new RuntimeException("Connection error to " + url, e);
        }

    }

    public List<Quote> scrapQuotes() {
        Document document = get("https://quotes.toscrape.com/");

        int pagesScraped = 0;
        List<Quote> quotes = new ArrayList<>();

        while (pagesScraped < MAX_PAGES_TO_SCRAP) {
            quotes.addAll(document.select(".quote").stream().map(this::getQuote).toList());

            Elements nextPageButtons = document.select(".pager .next a");
            if (nextPageButtons.isEmpty()) {
                break;
            }
            document = get(nextPageButtons.attr("abs:href"));
            pagesScraped++;
        }

        return quotes;
    }
}
