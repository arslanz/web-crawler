package org.codingexercise.buildit.webcrawler.crawler.checker;

/**
 * Interface for performing a boolean check on an URL.
 */
public interface UrlChecker {
    boolean test(String url);
}
