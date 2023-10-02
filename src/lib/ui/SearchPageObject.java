package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_RESULT_BY_STRING_TPL = "//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = '{SUBSTRING}']",
            SEARCH_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_LABEL = "//*[contains(@text, 'No results')]";


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_STRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput (){
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find element and click",5);
    }
    public void setSearchSkipButton(){
        //this.waitForElementPresent(By.id(SEARCH_SKIP_BUTTON), "Cannot find Skip button");
        this.waitForElementAndClick(By.id(SEARCH_SKIP_BUTTON), "Cannot tap to skip button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot type to search input", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    @Override
    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond) {
        return super.waitForElementNotPresent(by, error_message, timeOutInSecond);
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel button", 5);

    }

    public void waitForCancelButtonToDissappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel button is still present", 5);
    }
    public void clickCancelButton(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot tap to the Cancel button", 5);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find something by request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_LABEL),
                "Cannot find empty result label by request ",
                15
        );
    }

    public void assertThereIsNoResultsOfSearch(){
        this.assertElementsNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We've found some results"
        );
    }
    public void assertThereIsResultOfSearch(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementPresent(
                By.xpath(search_result_xpath),
                "We've not found results by text " + substring
        );
    }

}
