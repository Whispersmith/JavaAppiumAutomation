package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.ArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        }else{
            throw new RuntimeException("Has no platform " + Platform.getInstance());
        }
    }

}
