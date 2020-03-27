package org.codingexercise.buildit.webcrawler.crawler.checker;

/**
 * Checker class to determine whether a specified URL is within a particular domain.
 */
public class SameDomainChecker implements UrlChecker {
    private static final String DOMAIN_REGEX = "[a-z]+://(www\\.)?([\\w.]+)(\\.[\\w]+.*)$";
    private final UrlChecker validUrlChecker = new ValidUrlChecker();
    private final String domainName;

    public SameDomainChecker(final String urlWithDomain) {
        doPreChecks(urlWithDomain);
        domainName = extractDomainName(urlWithDomain);
    }

    @Override
    public boolean test(final String url) {
        final String domainToCheck = extractDomainName(url);
        if (null == url || url.length() == 0)
            return false;

        return domainToCheck.startsWith(domainName) || domainToCheck.endsWith(domainName);
    }

    private String extractDomainName(final String urlWithDomain) {
        return urlWithDomain.replaceAll(DOMAIN_REGEX, "$2");
    }

    private void doPreChecks(final String urlWithDomain) {
        if (null == urlWithDomain || urlWithDomain.length() == 0)
            throw new IllegalArgumentException("The URL cannot be null or empty.");
        else if (!validUrlChecker.test(urlWithDomain))
            throw new IllegalArgumentException("Invalid URL:" + urlWithDomain);
    }
}
