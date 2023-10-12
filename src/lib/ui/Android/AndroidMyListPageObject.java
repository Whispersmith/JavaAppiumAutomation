package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObjects;

public class AndroidMyListPageObject extends MyListsPageObjects {
    static {
        NOT_NOW_BUTTON = "org.wikipedia:id/negativeButton";
        FOLDER_BY_NAME_TPL = "//*[@resource-id = 'org.wikipedia:id/item_title_container']//*[@text = '{FOLDER_NAME}']";
        ARTICLE_TITLE_TPL = "//*[@text = '{TITLE}']";
    }
    public AndroidMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
