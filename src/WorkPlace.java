import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class WorkPlace {
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
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Test
//находим поле поиска, вводим там Java, смотрим выдачу поиска и ищем там Object-oriented programming language
    public void firstTest(){

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
               "Cannot find Skip",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find Object-oriented programming language in list",
                15
        );
    }

    @Test
//ищем поле ввода, пишем Java, очищаем ввод и проверяем что крестика нет на экране(что поиск закрыт)
    public void testCancelSearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find Cancel button",
                15

        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X still present in page",
                15
        );
    }

    @Test
//ищем поле ввода, пишем Java, нажимаем на выдачу в поиске, проеряем что выдалось на экране с ошибкой
    public void testCompareArticleTitle(){

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find input",
                15
        );
        WebElement title_element = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );
       String article_title = title_element.getText();

        Assert.assertEquals(
                "We see unexpected message",
                "Object-oriented programming language",
                article_title
        );

    }

    @Test
    public void testSwipeArticle(){

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find Element Search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Cannot find input",
                15
        );
        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );
//поправить локатор
        swipeUpToFindElement(
                By.xpath("//android.view.View[@content-desc = 'View article in browser']"),
                "Cannot find the end of the article",
                10
        );

    }
    @Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find input",
                5
        );
        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find button",
                5
        );

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put the text into articles folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press the OK button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot click to Back button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot click to Back button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc= 'Saved']"),
                "Cannot find Saved in bottom bar",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot close the dialog by Not now",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title_container']//*[@text = '"+name_of_folder+"']"),
                "Cannot find new saved article",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find Element Java (programming language) to swipe"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        String search_line = "Linkin park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find Element Search",
                5
        );
        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find something by request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        String search_line = "hfgdzgfxhh";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find Element Search",
                5
        );
        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[contains(@text, 'No results')]";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by request " + search_line,
                15
        );
        assertElementsNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );

    }

    @Test
    public void testChangeScreenOrientationOnSearchResult(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find Element Search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find Object-oriented programming language " + search_line,
                15
        );
        String title_before_rotation = waitForElementAndAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find Java",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find Object-oriented programming language " + search_line,
                5
        );
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find search result after returning from background",
                5
        );

    }

    @Test
    public void testSaveAndDeleteTwoArticles(){
            waitForElementAndClick(
                    By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Cannot find input",
                    5
            );
            String word_for_search = "Java";

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    word_for_search,
                    "Cannot find Element Search",
                    5
            );
            String first_article = "//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']";
            waitForElementAndClick(
                    By.xpath(first_article),
                    "Cannot find first article",
                    5
            );
            waitForElementPresent(
                    By.id("pcs-edit-section-title-description"),
                    "Cannot find article title",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/page_save"),
                    "Cannot find Save",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/snackbar_action"),
                    "Cannot find button",
                    5
            );

            String name_of_folder = "Learning programming";
            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot put the text into articles folder input",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@text = 'OK']"),
                    "Cannot press the OK button",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                    "Cannot click to Back button",
                    5
            );
            String second_article = "//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'JavaScript']";
            waitForElementAndClick(
                    By.xpath(second_article),
                    "Cannot find first article",
                    5
            );
            waitForElementPresent(
                    By.xpath("//android.view.View[@content-desc= 'JavaScript']"),
                    "Cannot find article title",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/page_save"),
                    "Cannot find the button Save",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/snackbar_action"),
                    "Cannot find the action button",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/list_of_lists']//*[contains(@text,'"+name_of_folder+"')]"),
                    "Cannot put article to the folder",
                    5
            );
           waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                    "Cannot click to Back button",
                    5
            );
           waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                    "Cannot click to Back button",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc= 'Saved']"),
                    "Cannot find Saved in bottom bar",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/negativeButton"),
                    "Cannot close the dialog by Not now",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title_container']//*[@text = '"+name_of_folder+"']"),
                    "Cannot find new saved article",
                    5
            );
            swipeElementToLeft(
                    By.xpath("//*[@text = 'Java (programming language)']"),
                    "Cannot find Element Java (programming language) to swipe"
            );

            waitForElementPresent(
                    By.xpath("//*[@text = 'JavaScript']"),
                    "Article was not delete",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@text = 'JavaScript']"),
                    "Cannot tap to item n the List",
                    5
            );
            waitForElementPresent(
                    By.xpath("//android.view.View[@content-desc= 'JavaScript']"),
                    "Cannot find article title",
                    5
            );

        }

        @Test
    public void testOpenArticleAndCheckTitle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot type "+search_line+" to the search line",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Java (programming language)']"),
                "Cannot find text Java (programming language)",
                5
        );
        assertElementPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                        "Cannot find title"
        );


    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action =new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();

    }
    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
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
    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/ 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

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
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.clear();
        return element;
    }
    private  int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    private void assertElementsNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements >0){
            String default_message = "'An element '" + by.toString() + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);

        }
    }
    private void assertElementPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if(amount_of_elements == 0){
            String default_message = "'An element '" + by.toString() + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    private String waitForElementAndAttribute(By by, String attribute, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by,error_message,timeOutInSeconds);
        return element.getAttribute(attribute);
        }

}
