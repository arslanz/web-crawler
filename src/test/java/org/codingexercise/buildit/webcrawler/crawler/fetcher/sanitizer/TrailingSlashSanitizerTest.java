package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TrailingSlashSanitizerTest {
    private final TrailingSlashSanitizer trailingSlashSanitizer = new TrailingSlashSanitizer();

    @Test
    public void testNullUrlReturnsNull() {
        assertNull(trailingSlashSanitizer.clean(null));
    }

    @Test
    public void testEmptyUrlReturnsNull() {
        assertEquals("", trailingSlashSanitizer.clean(""));
    }

    @Test
    public void testUrlWithNoTrailingSlashReturnsUnmodifiedUrl() {
        final String url = "https://www.google.com/index.html";
        assertEquals(url, trailingSlashSanitizer.clean(url));
    }

    @Test
    public void testUrlWithTrailingSlashReturnsUrlWithout() {
        final String url = "https://www.google.com/index.html/";
        final String expected = "https://www.google.com/index.html";
        assertEquals(expected, trailingSlashSanitizer.clean(url));
    }


}