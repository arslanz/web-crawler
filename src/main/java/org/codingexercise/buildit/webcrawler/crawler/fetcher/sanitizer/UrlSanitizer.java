package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

/**
 * Interface for sanitizing an URL to remove any unwanted elements.
 */
public interface UrlSanitizer {

    String clean(final String url);
}
