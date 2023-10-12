package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListPageObject;
import lib.ui.MyListsPageObjects;


public class MyListsPageObjectFactory {
    public static MyListsPageObjects get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }else{
            throw new RuntimeException("Has no platform " + Platform.getInstance());
        }
    }
}
