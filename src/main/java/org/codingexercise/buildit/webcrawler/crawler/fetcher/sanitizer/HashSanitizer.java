package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

/**
 * Class for removing the hash anchor section of an URL.
 * e.g. https://www.google.com/index#keyword
 * would be sanitized to become:
 * https://www.google.com/index
 */
public class HashSanitizer implements UrlSanitizer {

    @Override
    public String clean(final String url) {
        if (null == url || url.length() == 0)
            return url;

        final int index = url.indexOf('#');
        return index == -1 ? url : url.substring(0, index);
    }
}
