package org.codingexercise.buildit.webcrawler.crawler.checker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameDomainCheckerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullDomainUrlThrowsException() {
        new SameDomainChecker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDomainUrlThrowsException() {
        new SameDomainChecker("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomainUrlThrowsException() {
        new SameDomainChecker("www.google.com");
    }

    @Test
    public void testEmptyUrlReturnsFalse() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertFalse(sdChecker.test(""));
    }

    @Test
    public void testUrlWithinDomainReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertTrue(sdChecker.test("https://www.google.com/index.html"));
    }

    @Test
    public void testUrlDifferentProtocolButSameDomainReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("http://www.google.com");
        assertTrue(sdChecker.test("https://www.google.com/index.html"));
    }

    @Test
    public void testUrlDifferentAuthorityButSameDomainReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertTrue(sdChecker.test("https://www.google.net/index.html"));
    }

    @Test
    public void testUrlWithinDomainWithUrlParamsReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertTrue(sdChecker.test("https://www.google.net/search/q=build+it&browser=firefix&encoding=utc"));
    }

    @Test
    public void testUrlNotInDomainReturnsFalse() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertFalse(sdChecker.test("https://www.yahoo.com/index.html"));
    }

    @Test
    public void testUrlWithPrefixWithInDomainReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertTrue(sdChecker.test("https://www.account.google.com/index.html"));
    }

    @Test
    public void testUrlWithSuffixWithInDomainReturnsTrue() {
        final SameDomainChecker sdChecker = new SameDomainChecker("https://www.google.com");
        assertTrue(sdChecker.test("https://www.google.account.com/index.html"));
    }

}