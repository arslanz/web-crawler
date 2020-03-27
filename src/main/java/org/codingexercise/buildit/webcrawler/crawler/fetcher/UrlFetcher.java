package org.codingexercise.buildit.webcrawler.crawler.fetcher;

import org.codingexercise.buildit.webcrawler.crawler.checker.SameDomainChecker;
import org.codingexercise.buildit.webcrawler.crawler.checker.UrlChecker;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.pojo.FetchedUrls;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer.EverythingSanitizer;
import org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer.UrlSanitizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for fetching URL's referenced within a provided page URL.
 * This implementation uses the JSoup library to fetch and extract data.
 */
public class UrlFetcher implements Fetcher {
    private static final String ABSOLUTE_REF_URL_RESOLVER = "abs:href";
    private static final String ABSOLUTE_STATIC_URL_RESOLVER = "abs:src";
    private static final String REF_URL_QUERY = "a[href]";
    private static final String STATIC_URL_QUERY = "[src]";
    private final UrlSanitizer sanitizer;
    private final UrlChecker sameDomainChecker;

    public UrlFetcher(final String url) {
        sanitizer = new EverythingSanitizer();
        sameDomainChecker = new SameDomainChecker(url);
    }

    @Override
    public FetchedUrls fetch(final String url) {
        FetchedUrls fetchedUrls;
        try {
            final Document document = Jsoup.connect(url).get();
            final List<String> internalUrls = extractInternalUrls(document);
            final List<String> externalUrls = extractExternalUrls(document);
            final List<String> staticUrls = extractStaticUrls(document);
            fetchedUrls = createFetchedUrls(internalUrls, externalUrls, staticUrls);
        } catch (IOException e) {
            fetchedUrls = createErrorInFetchedUrls(e.getMessage());
        }
        return fetchedUrls;
    }

    private List<String> extractInternalUrls(final Document document) {
        return document.select(REF_URL_QUERY).stream()
                .map(e -> e.attr(ABSOLUTE_REF_URL_RESOLVER))
                .filter(sameDomainChecker::test)
                .map(sanitizer::clean)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> extractExternalUrls(final Document document) {
        return document.select(REF_URL_QUERY).stream()
                .map(e -> e.attr(ABSOLUTE_REF_URL_RESOLVER))
                .filter(e -> !sameDomainChecker.test(e))
                .map(sanitizer::clean)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> extractStaticUrls(final Document document) {
        return document.select(STATIC_URL_QUERY).stream()
                .map(e -> e.attr(ABSOLUTE_STATIC_URL_RESOLVER))
                .distinct()
                .collect(Collectors.toList());
    }


    private FetchedUrls createFetchedUrls(final List<String> internal, final List<String> external, final List<String> staticUrls) {
        return FetchedUrls.builder()
                .internalUrls(internal)
                .externalUrls(external)
                .staticUrls(staticUrls)
                .isError(false)
                .errorMessage("")
                .build();
    }

    private FetchedUrls createErrorInFetchedUrls(final String errorMessage) {
        return FetchedUrls.builder()
                .internalUrls(Collections.emptyList())
                .externalUrls(Collections.emptyList())
                .staticUrls(Collections.emptyList())
                .isError(true)
                .errorMessage(errorMessage)
                .build();
    }
}
