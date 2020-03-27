package org.codingexercise.buildit.webcrawler.pojo;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * A POJO class representing a single page's result from the crawler.
 */

@Value
@Builder
public class PageResult {
    private final String visitedPageUrl;
    private final boolean isErrorCrawling;
    private final String errorMessage;
    private final List<String> internalUrls;
    private final List<String> externalUrls;
    private final List<String> staticContentUrls;
}
