package org.codingexercise.buildit.webcrawler.crawler;

import org.codingexercise.buildit.webcrawler.crawler.fetcher.Fetcher;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.UrlFetcher;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.pojo.FetchedUrls;
import org.codingexercise.buildit.webcrawler.pojo.PageResult;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A Crawler implementation that takes a starting URL and iteratively searches across the breadth of the
 * internal domain URL references. As a result this crawler takes a breadth-first approach.
 */
class BreadthFirstCrawler implements Crawler {

    @Override
    public void crawl(final String url, final Consumer<PageResult> consumer) {
        final LinkedList<String> fetchQueue = new LinkedList<>();
        fetchQueue.add(url);
        crawlIteratively(fetchQueue, new UrlFetcher(url), consumer);
    }

    private void crawlIteratively(final Queue<String> fetchQueue, final Fetcher fetcher, final Consumer<PageResult> consumer) {
        final Set<String> completed = new HashSet<>();
        while (!fetchQueue.isEmpty()) {
            final String url = fetchQueue.poll();
            if (!completed.contains(url)) {
                final FetchedUrls fetchedUrls = fetcher.fetch(url);
                completed.add(url);
                sendToConsumer(url, fetchedUrls, consumer);

                for (final String newUrl : fetchedUrls.getInternalUrls()) {
                    if (!completed.contains(newUrl))
                        fetchQueue.add(newUrl);
                }
            }
        }
    }

    private void sendToConsumer(final String url, final FetchedUrls fetchedUrls, final Consumer<PageResult> consumer) {
        final PageResult pageResult = toPageResult(url, fetchedUrls);
        consumer.accept(pageResult);
    }

    private PageResult toPageResult(final String url, final FetchedUrls fetchedUrls) {
        return PageResult.builder()
                .visitedPageUrl(url)
                .isErrorCrawling(fetchedUrls.isError())
                .errorMessage(fetchedUrls.getErrorMessage())
                .internalUrls(fetchedUrls.getInternalUrls())
                .externalUrls(fetchedUrls.getExternalUrls())
                .staticContentUrls(fetchedUrls.getStaticUrls())
                .build();
    }

}
