import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and1");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/User/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

   @Test
    public void firstTest(){
       waitForElementAndClick(
               By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
               "Cannot find Search Wikipedia to click",
               5
       );

       assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Cannot find search"
        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message){
     WebElement element = waitForElementPresent(by, error_message);
     String text = element.getAttribute("text");
     Assert.assertEquals(
             "We see unexpected text",
             expected_text,
             text
     );
     return element;
    }

}










