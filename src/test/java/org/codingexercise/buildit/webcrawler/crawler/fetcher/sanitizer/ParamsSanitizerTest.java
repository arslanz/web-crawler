package org.codingexercise.buildit.webcrawler.crawler.fetcher.sanitizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParamsSanitizerTest {
    private final ParamsSanitizer paramsSanitizer = new ParamsSanitizer();

    @Test
    public void testNullUrlReturnsNull() {
        assertNull(paramsSanitizer.clean(null));
    }

    @Test
    public void testEmptyUrlReturnsNull() {
        assertEquals("", paramsSanitizer.clean(""));
    }

    @Test
    public void testUrlWithNoParamsReturnsUnmodifiedUrl() {
        final String url = "https://www.google.com/index.html";
        assertEquals(url, paramsSanitizer.clean(url));
    }

    @Test
    public void testUrlWithParamsReturnsUrlWithoutParams() {
        final String url = "https://www.google.com/index.html?q=params&source=firefox&encoding=utf";
        final String expected = "https://www.google.com/index.html";
        assertEquals(expected, paramsSanitizer.clean(url));
    }

}