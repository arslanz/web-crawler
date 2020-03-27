package org.codingexercise.buildit.webcrawler.crawler;

import org.codingexercise.buildit.webcrawler.pojo.PageResult;

import java.util.function.Consumer;

/**
 * Interface for the different implementations of a crawler which must take a starting
 * URL and a consumer to handle the output.
 */
public interface Crawler {

    void crawl(final String url, final Consumer<PageResult> resultConsumer);
}
