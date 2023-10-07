package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    //private static final String PLATFORM_IOS = "ios";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;
    private Platform(){}
    public static Platform getInstance(){
        if(instance == null ){
            instance = new Platform();
        }
        return instance;
    }


   public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }else {
            throw new Exception("Cannot detect type of the driver . Platform value: "+ this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return  isPlatform(PLATFORM_ANDROID);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and1");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/User/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }




    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }


    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }

}
