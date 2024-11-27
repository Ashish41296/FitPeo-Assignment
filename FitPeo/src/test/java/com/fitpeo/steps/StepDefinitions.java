package com.fitpeo.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.After; 
import org.openqa.selenium.WebDriver;  

import static org.junit.Assert.*;

import java.time.Duration;

public class StepDefinitions {

    WebDriver driver;

    @Given("I am on the FitPeo homepage")
    public void i_am_on_the_FitPeo_homepage() {
        // Set up WebDriverManager to manage ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Add the argument to start the browser maximized
        options.addArguments("--start-maximized");

        // Initialize WebDriver with the ChromeOptions
        driver = new ChromeDriver(options);

        // Navigate to the FitPeo home page
        driver.get("https://www.fitpeo.com");

    }

    @When("I navigate to the Revenue Calculator page")
    public void i_navigate_to_the_Revenue_Calculator_page() throws Throwable {
    	Thread.sleep(5000);
        WebElement revenueCalculator = driver.findElement(By.xpath("//div[text()='Revenue Calculator']"));
        revenueCalculator.click();
    }

    @When("I scroll down to the slider section")
    public void i_scroll_down_to_the_slider_section() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-79elbk']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
    }
    
    


    
    @When("I adjust the slider to {int}")
    public void i_adjust_the_slider_to(int targetValue) throws InterruptedException {
        
        // Maximum value corresponding to 100% on the slider
        int maxValue = 2000;  
        double percentage = (double) targetValue / maxValue * 100;  // Calculate the corresponding percentage

        // Convert the percentage to the 'left' style value for the slider (0% to 100%)
        String leftValue = percentage + "%";

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Locate the slider element
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']"));

        // Set the 'left' style of the slider's thumb element based on the calculated percentage
        js.executeScript("arguments[0].setAttribute('style', 'left: " + leftValue + ";')", slider);
        
        // Wait for the slider to settle after the adjustment
        Thread.sleep(1000);  // Sleep for 1 second 

        // Click on the slider to simulate the action
        slider.click();
        
        // Locate the element containing the 'value' attribute 
        WebElement valueBoxElement = driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']")); // Replace with the appropriate locator

        // Fetch the value from the 'value' attribute
        String valueBox = valueBoxElement.getAttribute("value");

        // Convert the fetched value to an integer
        int fetchedValue = Integer.parseInt(valueBox);

        // Calculate the difference between the target and fetched value
        int difference = targetValue - fetchedValue;

        // Determine if we need to increase or decrease the slider
        if (difference > 0) {
        	
            // Target value is greater, simulate right arrow key
            for (int i = 0; i < difference; i++) {
                // Press the right arrow key
                new Actions(driver).sendKeys(Keys.ARROW_RIGHT).build().perform();
                
                
                // Sleep to give time for the slider to adjust
                Thread.sleep(2000);
            }
        } else if (difference < 0) {
        	
            // Target value is less, simulate left arrow key
            for (int i = 0; i < Math.abs(difference); i++) {
                // Press the left arrow key
                new Actions(driver).sendKeys(Keys.ARROW_LEFT).build().perform();
                
                
                // Sleep to give time for the slider to adjust
                Thread.sleep(2000);
            }
        }
    }
    
    
    @Then("the text field should show {int}")
    public void the_text_field_should_show(int value) {
        WebElement textField = driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));
        String textValue = textField.getAttribute("value");
        assertEquals("Text field value does not match expected", String.valueOf(value), textValue);
    }
    
   
    
    @When("I enter {int} into the text field")
    public void i_enter_into_the_text_field(int value) {
        // Locate the text field element
        WebElement textField = driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));

       
        // Click to focus on the text field
        textField.click();
        
        // Clear the text field 
        textField.sendKeys(Keys.CONTROL + "a"); // Select all text in the input
        textField.sendKeys(Keys.DELETE); // Delete selected text

        // Type the new value
        textField.sendKeys(String.valueOf(value));
    }

    
    
    @Then("the slider should adjust to {int}")
    public void the_slider_should_adjust_to(int value) throws InterruptedException {
        // The maximum value corresponding to 100% on the slider
        int maxValue = 2000;  

        // Calculated the percentage based on the target value (where 2000 is 100%)
        double percentage = (double) value / maxValue * 100;  // Calculate the corresponding percentage
        
        int intPercentage = (int) percentage;

        // Convert the percentage to the 'left' style value for the slider (0% to 100%)
        String expectedLeftValue = intPercentage + "%";
        

        // Locate the slider element (slider thumb)
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']"));

        // Wait for the slider to adjust 
        Thread.sleep(5000);  
        
        // Get the actual 'left' value of the slider (this represents its position)
        String sliderStyle = slider.getAttribute("style");
        
        
        // Check if the slider's left position matches the expected percentage
        assertTrue("Slider did not adjust to the expected position",
                sliderStyle.contains("left: " + expectedLeftValue));
    }
    
    

    @When("I select the CPT codes CPT-99091, CPT-99453, CPT-99454, and CPT-99474 for Total Individual Patient per Month {int}")
    public void i_select_the_CPT_codes_for_total_individual_patient_per_month(int value) throws Throwable {
	
        // Locate and interact with the text field to set the correct Total Individual Patient/Month value
        WebElement textField = driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));

        Thread.sleep(3000);
        // Clear and set the value in the text field (to simulate entering the desired value)
        textField.click();
        textField.sendKeys(Keys.CONTROL + "a");  // Select all text in the input
        textField.sendKeys(Keys.DELETE);  // Delete selected text
        textField.sendKeys(String.valueOf(value));  // Type the new value (Total Individual Patient/Month)

        // Select the CPT-99091 code
        WebElement cpt99091 = driver.findElement(By.xpath("//p[text()='CPT-99091']/following-sibling::p/following-sibling::div/following-sibling::label/span/input"));
        cpt99091.click();

        // Select the CPT-99453 code
        WebElement cpt99453 = driver.findElement(By.xpath("//p[text()='CPT-99453']/following-sibling::p/following-sibling::div/following-sibling::label/span/input"));
        cpt99453.click();

        // Select the CPT-99454 code
        WebElement cpt99454 = driver.findElement(By.xpath("//p[text()='CPT-99454']/following-sibling::p/following-sibling::div/following-sibling::label/span/input"));
        cpt99454.click();

        // Scroll to and select the CPT-99474 code
        
      
        WebElement cpt99474Box = driver.findElement(By.xpath("//span[text()='Can be billed twice in a month']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cpt99474Box);  // Scroll to the CPT-99474 checkbox
       
        
        Thread.sleep(5000);
        
        WebElement cpt99474 = driver.findElement(By.xpath("//p[text()='CPT-99474']/following-sibling::p/following-sibling::div/following-sibling::label/span/input"));
        
        cpt99474.click();  // Select the CPT-99474 code

        // Wait to ensure all interactions are complete
        Thread.sleep(5000); 
    }
    
    
    
    @Then("the Total Recurring Reimbursement should be {string}")
    public void the_Total_Recurring_Reimbursement_should_be(String expectedValue) {
        // Locate the actual Total Recurring Reimbursement element (adjust the locator as needed)
        WebElement reimbursementElement = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'])[4]"));  // Update with actual locator

        // Get the actual text from the element
        String actualText = reimbursementElement.getText();

        // Extract the actual reimbursement amount (in this case, "$110700") from the full text
        String actualAmount = actualText.replaceAll("Total Recurring Reimbursement for all Patients Per Month:", "").trim();

        // Compare the extracted amount to the expected value
        assertEquals(expectedValue, actualAmount);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}