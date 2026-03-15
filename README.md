# Highcharts UI Tests

Automated UI tests for the **Highcharts Combo Timeline** demo page using Selenium WebDriver, Cucumber, and TestNG.

## Tech Stack

* Java
* Maven
* Selenium WebDriver
* Cucumber (BDD)
* TestNG
  
## Project Structure

```
highcharts-ui-tests
│
├── src
│   └── test
│       ├── java
│       │   ├── config
│       │   ├── cucumber
│       │   ├── driver
│       │   │   └── DriverManager.java
│       │   └── pages
│       │
│       └── resources
│           ├── features
│           │   └── comboTimeline.feature
│           └── config.properties
│
├── pom.xml
├── testng.xml
└── .gitignore
```

## Test Scenario

The current automated test verifies that:
* the **Highcharts Combo Timeline** demo page opens successfully
* cookie consent is handled if it appears
* the main page content loads correctly

## Configuration

Test configuration is stored in:

```
src/test/resources/config.properties
```

This file contains settings such as:
* browser type
* base URL
* timeouts

## Purpose
This project demonstrates a basic **UI automation testing framework** built with Selenium, Cucumber, and TestNG.
It provides a foundation for extending automated UI tests for Highcharts demo pages.
