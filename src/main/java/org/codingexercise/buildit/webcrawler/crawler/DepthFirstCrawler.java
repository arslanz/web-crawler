package org.codingexercise.buildit.webcrawler.crawler;

import org.codingexercise.buildit.webcrawler.crawler.fetcher.Fetcher;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.UrlFetcher;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.pojo.FetchedUrls;
import org.codingexercise.buildit.webcrawler.pojo.PageResult;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A Crawler implementation that takes a starting URL and recursively searches within the internal domain
 * URL references for content. As a result this crawler takes a depth-first approach.
 */
class DepthFirstCrawler implements Crawler {

    @Override
    public void crawl(final String url, final Consumer<PageResult> consumer) {
        crawlRecursively(url, getFetcher(url), new HashSet<>(), consumer);
    }

    protected UrlFetcher getFetcher(final String url) {
        return new UrlFetcher(url);
    }

    private void crawlRecursively(final String url, final Fetcher fetcher, final Set<String> completed, final Consumer<PageResult> consumer) {
        final FetchedUrls fetchedUrls = fetcher.fetch(url);
        completed.add(url);
        sendToConsumer(url, fetchedUrls, consumer);

        for (final String nextUrl : fetchedUrls.getInternalUrls()) {
            if (!completed.contains(nextUrl))
                crawlRecursively(nextUrl, fetcher, completed, consumer);
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
