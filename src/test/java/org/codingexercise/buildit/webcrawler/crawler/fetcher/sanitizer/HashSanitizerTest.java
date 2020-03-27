package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashSanitizerTest {
    private final HashSanitizer hashSanitizer = new HashSanitizer();

    @Test
    public void testNullUrlReturnsNull() {
        assertNull(hashSanitizer.clean(null));
    }

    @Test
    public void testEmptyUrlReturnsNull() {
        assertEquals("", hashSanitizer.clean(""));
    }

    @Test
    public void testUrlWithNoHashReturnsUnmodifiedUrl() {
        final String url = "https://www.google.com/index.html";
        assertEquals(url, hashSanitizer.clean(url));
    }

    @Test
    public void testUrlWithHashReturnsUrlWithoutHashFollowingChars() {
        final String url = "https://www.google.com/index.html#anchorPos1";
        final String expected = "https://www.google.com/index.html";
        assertEquals(expected, hashSanitizer.clean(url));
    }

}