package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/RevenueCalculator.feature",  // Path to the feature file
        glue = {"com.fitpeo.steps"},  // Path to the step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"},  // Plugins for reporting
        monochrome = true,  // Makes the console output more readable
        publish = true  // Enables publishing of the test results 
)
public class TestRunner {
    // The class doesn't require any methods as JUnit handles the execution
}