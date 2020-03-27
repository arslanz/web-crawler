package org.codingexercise.buildit.webcrawler.crawler.checker;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Checker class to determine whether a string is a correctly formed URL.
 */
public class ValidUrlChecker implements UrlChecker {
    private final UrlValidator urlValidator = new UrlValidator();

    public boolean test(final String input) {
        return urlValidator.isValid(input);
    }
}
