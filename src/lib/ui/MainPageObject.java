package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }


    public void swipeUp(int timeOfSwipe){
        TouchAction action =new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();

    }
    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes){

        int already_swiped = 0;
//        int size = driver.findElements(by).size();
//        System.out.println(size);

        while(driver.findElements(by).size() == 0){
            if(already_swiped > max_swipes){
                waitForElementPresent(by,"Cannot find element by swiping up.\n"+ error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    public void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/ 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

    }


    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }
    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond){
    WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClear(By by, String error_message, long timeOutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.clear();
        return element;
    }
    public int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementsNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements >0){
            String default_message = "'An element '" + by.toString() + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);

        }
    }
    public void assertElementPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if(amount_of_elements == 0){
            String default_message = "'An element '" + by.toString() + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    public String waitForElementAndAttribute(By by, String attribute, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by,error_message,timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    /*public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator,"Cannot find element by locator", 5).getLocation().getY();
        int screen_size_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_y;
    }
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped>max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }*/

}
