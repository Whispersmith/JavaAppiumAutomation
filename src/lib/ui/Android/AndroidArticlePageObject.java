package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
            TITLE = "pcs-edit-section-title-description";
            FOOTER_ELEMENT = "//android.view.View[@content-desc = 'View article in browser']";
            SAVE_BUTTON = "org.wikipedia:id/page_save";
            ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action";
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
            OK_BUTTON = "//*[@text = 'OK']";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
