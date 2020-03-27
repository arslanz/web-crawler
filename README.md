# web-crawler

## Overview
This is a simple Web Crawler written in Java as a maven project.

The purpose of the project is to take in a user provided starting URL, crawl all the containing URL's and follow the references to other pages as long as they are within the same domain as the starting URL. For each page, the crawler collects the internal URL references, external URL references and the references to static content used on the page.

## Prerequisites
Before being able to compile, test and run you will need to have/install:
* Apache Maven
* Java 8 - minimum
* active internet connection!

## Compiling
`mvn clean compile`

## Running the unit tests
`mvn clean test`

## Running the code
`mvn exec:java -Dexec.mainClass=org.codingexercise.buildit.webcrawler.EntryPoint`

# Code design and approach
The design has been separated into three main components; crawler, fetcher and consumer. The crawler is responsible for visiting the various pages using the fetcher and then sending the result to the consumer.
