package org.codingexercise.buildit.webcrawler.crawler.fetcher.pojo;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * A POJO class representing a single page's fetcher result.
 */
@Value
@Builder
public class FetchedUrls {
    private final List<String> internalUrls;
    private final List<String> externalUrls;
    private final List<String> staticUrls;
    private final boolean isError;
    private final String errorMessage;
}
