package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
        NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc= 'Navigate up']",
        SAVED_ARTICLES = "//android.widget.FrameLayout[@content-desc= 'Saved']";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void navigateUpFromArticle(){
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot click to Back button",
                5
        );
    }

    public void goToSavedArticles(){
        this.waitForElementAndClick(
                By.xpath(SAVED_ARTICLES),
                "Cannot find Saved in bottom bar",
                5
        );
    }


}
