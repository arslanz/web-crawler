package org.codingexercise.buildit.webcrawler;

import org.codingexercise.buildit.webcrawler.consumer.ConsolePrinter;
import org.codingexercise.buildit.webcrawler.crawler.Crawler;
import org.codingexercise.buildit.webcrawler.crawler.CrawlerFactory;
import org.codingexercise.buildit.webcrawler.crawler.checker.UrlChecker;
import org.codingexercise.buildit.webcrawler.crawler.checker.ValidUrlChecker;
import org.codingexercise.buildit.webcrawler.pojo.PageResult;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * This class is responsible for initialization of the crawler. It handles:
 * setting up the consumer,
 * selecting the right kind of crawler (depth/breadth),
 * receiving user input,
 * invoking the crawler with the starting URL.
 */
public class Initializer {
    private final static String EXIT_KEYWORD = "EXIT";
    private final UrlChecker urlValidator = new ValidUrlChecker();
    private final Crawler crawler = CrawlerFactory.createDepthFirstCrawler();
//    private final Crawler crawler = CrawlerFactory.createBreadthFirstCrawler();
    private final Consumer<PageResult> resultConsumer = new ConsolePrinter();

    public void begin() {
        String userInput = getValidUserEnteredUrl();
        if (!EXIT_KEYWORD.equalsIgnoreCase(userInput)) {
            final LocalDateTime start = LocalDateTime.now();
            crawler.crawl(userInput, resultConsumer);
            final LocalDateTime end = LocalDateTime.now();
            System.out.println("--------------------------------------------------");
            System.out.printf("\nTime taken to crawl %s: %s\n", userInput, Duration.between(start, end));
        }
    }

    private String getValidUserEnteredUrl() {
        boolean isValidEntry = false;
        String userInput;

        do {
            userInput = captureUserEnteredUrl();
            if (isValidUrl(userInput))
                isValidEntry = true;
            else
                displayInvalidUrlMessage(userInput);
        } while (!isValidEntry);

        return userInput;
    }

    private void displayInvalidUrlMessage(final String userInput) {
        System.out.println("This is not a valid URL: " + userInput);
    }

    private String captureUserEnteredUrl() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the starting URL (or 'exit' to end):");
        return input.nextLine().trim();
    }

    private boolean isValidUrl(final String userInput) {
        return EXIT_KEYWORD.equalsIgnoreCase(userInput) || urlValidator.test(userInput);
    }

}
