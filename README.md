# FitPeo-Assignment

#Selenium Java with Cucumber - Setup and Run Instructions

This project demonstrates how to run Selenium tests using Cucumber in Java. It is designed to automate web interactions with the browser, following Gherkin syntax for feature files and Cucumber step definitions.

# Prerequisites
Before getting started, ensure you have the following tools installed:

Java JDK (version 8 or higher)
Download and install from Oracle JDK.

Maven (for managing dependencies)
Download and install from Apache Maven.

Eclipse IDE (or your preferred Java IDE)
Download Eclipse.

Web Browser Driver (ChromeDriver for Google Chrome)
Download from ChromeDriver or use WebDriverManager.


# Step 1: Clone the Repository
Clone this repository to your local machine using the following command:

git clone <repository-url>


# Step 2: Project Setup
Open the project in Eclipse IDE (or your preferred IDE).
Maven will automatically download the required dependencies once the project is opened. You can also run mvn clean install from the command line to ensure all dependencies are downloaded.
Step 3: Project Dependencies
In the pom.xml file, ensure that you have the following dependencies to support Selenium, Cucumber, JUnit, and WebDriverManager:

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>Fitpeo</groupId>
  <artifactId>Fitpeo</artifactId>
  <version>0.0.1-SNAPSHOT</version>

  <dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.9.0</version>
    </dependency>

    <!-- Cucumber Java -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.11.0</version>
    </dependency>

    <!-- Cucumber JUnit -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.11.0</version>
    </dependency>

    <!-- JUnit -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>

    <!-- WebDriverManager to handle browser drivers -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.9.2</version>
    </dependency>
  </dependencies>
</project>


# Step 4: Run the Tests

You can run the tests using following method:

Running with JUnit
Open the Runner Class:
Ensure the Runner class is already set up and contains the proper annotations (@RunWith(Cucumber.class) and @CucumberOptions).

Run the Tests:
Right-click on the Runner.java class in your IDE and select Run As > JUnit Test. This will execute all the Cucumber scenarios defined in your feature files.



# Step 5: View the Test Results
After running the tests, you will see the results in the Eclipse console or terminal.
If the tests pass, a success message will be displayed.
If there are failures, Cucumber will display details about the failed steps and scenarios.
Viewing Detailed Test Results in the target Folder
In addition to the console output, you can find detailed test reports in the target folder of your project.

1.Navigate to the target directory in your project folder.

2.Open the cucumber-reports folder inside the target directory.

The folder contains HTML reports that provide detailed information about the test execution, including:
Feature files being tested.
Passed and failed scenarios.
Step-wise details of each test.
3.Open the HTML report (cucumber.html or similar) in your browser to view a formatted, easy-to-read report.

This report provides a summary of all the test scenarios executed and their results, helping you to easily track test outcomes and diagnose any issues in your automation scripts.
