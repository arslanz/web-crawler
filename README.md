# web-crawler

## Overview
This is a simple Web Crawler written in Java as a maven project.

The purpose of the project is to take a user provided starting URL, crawl all the containing URL's and, as long as they are within the same domain, follow the references to other pages. For each page, the crawler prints the internal URL references, external URL references and the references to static content used on the page.

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

Once initialized, the code will prompt the user for a URL. Please note that a fully formed URL (with the protocol) must be specified e.g.
```
**************************************************
Welcome to the BuildIt Web Crawler!
Please enter the starting URL (or 'exit' to end):
https://wiprodigital.com 
-----------------------------------------------
```

## Code design and approach
The design has been separated into three main components; Crawler, Fetcher and Consumer.

![alt text](https://github.com/arslanz/web-crawler/blob/master/design/WebCrawlerDesign.png "Web Crawler Design")

### Crawler
The Crawler is responsible for visiting the various pages using the Fetcher and then sending the result to the Consumer.
There are two main implementations of the Crawler; DepthFirstCrawler and BreadthFirstCrawler. As the name implies, they take two different approaches to searching for links within pages. In interesting observation is that, as you can see from the footer of the [sample output](https://github.com/arslanz/web-crawler/tree/master/sample), the BreadthFirstCrawler is slightly faster (7.8secs vs 8.5secs).

### Fetcher
The Fetcher is responsible for taking an URL and organising all the URL's on the page into internal, external and static content links. It does this using various other interfaces and classes; the Checker and Sanitizer classes. The results are returned to the Crawler.

The Fetcher uses a SameDomainChecker which by design assumes that the following are in the same domain:
https://www.google.com
https://www.google/index.html
https://google/index.html
https://account.google.com/index.html

The SameDomainChecker ignores the authority (.com, .net, .gov) as well as the www portion of the URL.

### Consumer
Whenever called by the Crawler, the consumer recieves a PageResult object that contains the three different categories of information to display: internal URL's, external URL's and static content URL's. Currently, there is one interface implementation named ConsolePrinter, which prints directly to the console. However, the design allows any other kind of implementation so that the results can be consumed by various interfaces or storage types.

## Future enhancements & features
* Expand the unit test coverage to cover all the classes.
* Include integration tests to perform automated end-to-end testing.
* Include performance tests to compare and contrast the different Crawler implementations.
* Multi-threaded Crawler implementations.
