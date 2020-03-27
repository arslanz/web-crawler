package org.codingexercise.buildit.webcrawler;

/**
 * Entry point into the application.
 */

public class EntryPoint {

    public static void main(final String[] args) {
        displayWelcome();
        Initializer init = new Initializer();
        init.begin();
        displayGoodbye();
    }

    private static void displayWelcome() {
        System.out.println("**************************************************");
        System.out.println("Welcome to the BuildIt Web Crawler!");
    }


    private static void displayGoodbye() {
        System.out.println("\nThank you for using the BuildIt Web Crawler!");
        System.out.println("**************************************************");
    }
}
