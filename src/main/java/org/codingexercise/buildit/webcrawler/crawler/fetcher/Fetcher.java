package org.codingexercise.buildit.webcrawler.crawler.fetcher;

import org.codingexercise.buildit.webcrawler.crawler.fetcher.pojo.FetchedUrls;

/**
 * Interface intended for fetching a list of elements from a URL string passed in.
 */
public interface Fetcher {

    FetchedUrls fetch(final String url);

}
