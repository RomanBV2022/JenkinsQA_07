package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.time.Duration;

import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.assertEquals;

public class GroupJavaAutomationTest {

    @Test
    public void herokuappHomePageTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        assertEquals(title, "The Internet");
        driver.quit();
    }
    @Test
    public void herokuAppAddRemoveTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");
        try {
            WebElement addRemove = driver.findElement(By.xpath("//a[@href='/add_remove_elements/']"));
            addRemove.click();
            WebElement addElement = driver.findElement(By.xpath("//*[@id='content']/div/button"));
            addElement.click();
            //explicit expectation - declaration, initialization
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //explicitly wait for the visibility of the button element
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"elements\"]/button[1]")));
            //clicking on the button, this action will only happen when the button element is visible on the page
            driver.findElement(By.xpath("//*[@id=\"elements\"]/button[1]")).click();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void herokuappABTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        WebElement buttonABTesting = driver.findElement(By.xpath("//a[@href='/abtest']"));
        buttonABTesting.click();
        WebElement textABTest = driver.findElement(By.xpath("//h3[text()='A/B Test Control']"));
        String abTestTitle = textABTest.getText();
        assertEquals(abTestTitle, "A/B Test Control");
        driver.quit();
    }

    @Test
    public void herokuappCheckBoxTest() {
        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/");
        WebElement buttonCheckBox = driver.findElement(By.xpath("//a[@href='/checkboxes']"));
        buttonCheckBox.click();
        WebElement textCheckBox = driver.findElement(By.xpath("//h3[text()='Checkboxes']"));
        String titleCheckBox = textCheckBox.getText();
        softAssert.assertEquals(titleCheckBox, "Checkboxes");
        List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        softAssert.assertEquals(listCheckbox.size(), 2);
        softAssert.assertAll();
        driver.quit();
    }

    @Test
    public void testAddElement() {
        List<String> expectedButtonsName = new ArrayList<>(List.of("Add Element","Delete"));

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        Wait<WebDriver> wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement addButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Add Element']")));
        addButton.click();

        List<WebElement> buttons = wait5.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//button"))));
        List<String> actualButtonsName = new ArrayList<>();
        for(WebElement button : buttons){
            actualButtonsName.add(button.getText());
        }

        Assert.assertEquals(actualButtonsName, expectedButtonsName);

        driver.quit();
    }

    @Test
    public void testBasicAuth() {
        WebDriver driver = new ChromeDriver();
        Wait<WebDriver> wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("the-internet.herokuapp.com");
        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "admin"));
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        String authMessage = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p"))).getText();

        Assert.assertEquals(authMessage, "Congratulations! You must have the proper credentials.");
    }

}

