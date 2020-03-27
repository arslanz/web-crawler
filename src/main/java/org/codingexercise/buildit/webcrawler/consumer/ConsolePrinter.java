package org.codingexercise.buildit.webcrawler.consumer;

import org.codingexercise.buildit.webcrawler.pojo.PageResult;

import java.util.List;
import java.util.function.Consumer;

public class ConsolePrinter implements Consumer<PageResult> {


    @Override
    public void accept(final PageResult pageResult) {
        System.out.println("-----------------------------------------------");
        System.out.println("Page URL: " + pageResult.getVisitedPageUrl());

        printErrorsEncountered(pageResult);
        printContent(pageResult);
    }

    private void printContent(final PageResult pageResult) {
        System.out.println("\nInternal URL references:");
        printUrls(pageResult.getInternalUrls());

        System.out.println("External URL references:");
        printUrls(pageResult.getExternalUrls());

        System.out.println("Static content references:");
        printUrls(pageResult.getStaticContentUrls());
    }

    private void printErrorsEncountered(final PageResult pageResult) {
        if (pageResult.isErrorCrawling()) {
            System.out.println("An error was encountered whilst crawling this page:");
            System.out.println(pageResult.getErrorMessage());
        }
    }

    private void printUrls(final List<String> urls) {
        int i = 1;
        for (final String url : urls)
            System.out.printf("%d. %s\n", i++, url);
    }

}
