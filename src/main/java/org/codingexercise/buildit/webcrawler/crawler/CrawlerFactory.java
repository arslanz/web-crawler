package org.codingexercise.buildit.webcrawler.crawler;

/**
 * Factory class to help the crawler user to easily select a crawler for their needs.
 */
public class CrawlerFactory {
    private static final String CRAWLER_TYPE_DEPTH = "depth";
    private static final String CRAWLER_TYPE_BREADTH = "breadth";

    /**
     * Returns a Crawler based on the String parameter.
     */

    public static Crawler createCrawler(final String crawlerType) {
        if (CRAWLER_TYPE_DEPTH.equals(crawlerType))
            return CrawlerFactory.createDepthFirstCrawler();
        else if (CRAWLER_TYPE_BREADTH.equals(crawlerType))
            return CrawlerFactory.createBreadthFirstCrawler();
        else
            throw new IllegalArgumentException("Unsupported Crawler type: " + crawlerType);
    }

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
