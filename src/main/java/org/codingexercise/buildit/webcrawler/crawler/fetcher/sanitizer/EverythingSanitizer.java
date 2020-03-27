package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to encompass all the sanitizers for ease of application.
 */
public class EverythingSanitizer implements UrlSanitizer {
    private final List<UrlSanitizer> sanitizers = initSanitizers();

    @Override
    public String clean(final String url) {
        String sanitizedUrl = url;

        for (final UrlSanitizer sanitizer : sanitizers)
            sanitizedUrl = sanitizer.clean(sanitizedUrl);

        return sanitizedUrl;
    }

    private List<UrlSanitizer> initSanitizers() {
        final List<UrlSanitizer> sanitizers = new ArrayList<>();

        sanitizers.add(new HashSanitizer());
        sanitizers.add(new ParamsSanitizer());
        sanitizers.add(new TrailingSlashSanitizer());

        return sanitizers;
    }
}
