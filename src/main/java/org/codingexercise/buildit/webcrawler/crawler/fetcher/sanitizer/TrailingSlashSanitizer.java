package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

/**
 * Class for removing the last trailing forward slash in an URL.
 * e.g. https://www.google.com/
 */
public class TrailingSlashSanitizer implements UrlSanitizer {

    @Override
    public String clean(final String url) {
        if (null == url || url.length() == 0)
            return url;

        final int lastIndex = url.length() - 1;
        char lastChar = url.toCharArray()[lastIndex];
        return lastChar == '/' ? url.substring(0, lastIndex) : url;
    }
}
