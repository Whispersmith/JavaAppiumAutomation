package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends  MainPageObject{
    private static final String
        TITLE = "pcs-edit-section-title-description",
        FOOTER_ELEMENT = "//android.view.View[@content-desc = 'View article in browser']",
        SAVE_BUTTON = "org.wikipedia:id/page_save",
        ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        OK_BUTTON = "//*[@text = 'OK']";


    public ArticlePageObject(AppiumDriver driver){
            super(driver);
        }
    public WebElement waitForTitleArticle(){
            return this.waitForElementPresent(By.id(TITLE), "Cannot find title", 5);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleArticle();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of article", 5);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find button",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put the text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot press the OK button",
                5
        );
    }

    public void addArticleToMyOldList() {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find button",
                5
        );

    }


}
