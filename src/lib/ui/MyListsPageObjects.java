package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObjects extends MainPageObject{
    public static final String
        NOT_NOW_BUTTON = "org.wikipedia:id/negativeButton",
        FOLDER_BY_NAME_TPL = "//*[@resource-id = 'org.wikipedia:id/item_title_container']//*[@text = '{FOLDER_NAME}']",
        ARTICLE_TITLE_TPL = "//*[@text = '{TITLE}']";

    public MyListsPageObjects(AppiumDriver driver){
        super(driver);
    }

    public void clickNotNow(){
        this.waitForElementAndClick(
                By.id(NOT_NOW_BUTTON),
                "Cannot close the dialog by Not now",
                5
        );
    }
    private static String getFolderByXpath(String name_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_folder);
    }

    public void openFolderByName(String name_folder){
        String folder_name_xpath = getFolderByXpath(name_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name "+ name_folder,
                5
        );
    }
    public static String getTitleByXpath(String article_title){
        return ARTICLE_TITLE_TPL.replace("{TITLE}",article_title);
    }

    public void waitForArticleAppear(String article_title){
        String article_xpath = getTitleByXpath(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find article " + article_xpath,
                5);
    }
    public void waitForArticleDisapear(String article_title){
        String article_xpath = getTitleByXpath(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Article is still present",
                5);
    }

    public void swipeArticleToDelete(String article_title){
        String article_xpath = getFolderByXpath(article_title);
        this.waitForArticleAppear(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find article title "+ article_xpath+ " for swipe"
        );
        this.waitForArticleDisapear(article_title);
    }



}
