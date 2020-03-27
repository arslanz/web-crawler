package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

/**
 * Class for removing parameters section of an URL.
 * e.g. https://www.google.com/search?q=keyword&source=firefox
 * would be sanitized to become:
 * https://www.google.com/search
 */
public class ParamsSanitizer implements UrlSanitizer {

    @Override
    public String clean(final String url) {
        if (null == url || url.length() == 0)
            return url;

        final int index = url.indexOf('?');
        return index == -1 ? url : url.substring(0, index);
    }
}
