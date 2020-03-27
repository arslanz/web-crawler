package org.codingexercise.buildit.webcrawler.crawler;

/**
 * Factory class to help the crawler user to easily select a crawler for their needs.
 */
public class CrawlerFactory {

    /**
     * Returns a Crawler that takes a starting URL and iteratively searches across the breadth of the
     * internal domain URL references.
     * @return DepthFirstCrawler
     */
    public static Crawler createDepthFirstCrawler() {
        return new DepthFirstCrawler();
    }

    /**
     * Returns a Crawler that takes a starting URL and recursively searches within the internal domain
     * URL references for content.
     * @return BreadthFirstCrawler
     */
    public static Crawler createBreadthFirstCrawler() {
        return new BreadthFirstCrawler();
    }
}
