package io.percy.appiumpercy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.percy.appium.AppPercy;

public class Android {
    private static AppPercy percy;

    // Hub Url to connect to Automation session
    private static String HUB_URL = "http://hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Browserstack specific capabiilities
        capabilities.setCapability("browserstack.user", "sakthisaravanan_kV8Ke1");
        capabilities.setCapability("browserstack.key", "SR1xmnYpdvbfy2VTi1vF");
        capabilities.setCapability("browserstack.appium_version", "1.20.2");

        // Percy Options
        capabilities.setCapability("percy.enabled", "true");
        capabilities.setCapability("percy.ignoreErrors", "true");

        // App url we get post uploading in response
        capabilities.setCapability("app", "bs://39990cefc9fda23b47c26e6391648177fe29025f"); //NWB
//        capabilities.setCapability("app","bs://be275518512cb6d05746dc210e71ca7488cf067c"); //NWO
//        capabilities.setCapability("app","bs://12e574fb6082a697f6382a233c25dac0f53fb707"); //IOM
        capabilities.setCapability("device", "Google Pixel 5");
        capabilities.setCapability("os_version", "11.0");
        capabilities.setCapability("project", "Java Project");

        // Create sessioin
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL(HUB_URL), capabilities);

        // Initialize AppPercy
        percy = new AppPercy(driver);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Take First Screenshot
        percy.screenshot("First Screenshot");


//        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
//            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
//        searchElement.click();
//
//        AndroidElement textInput = (AndroidElement) new WebDriverWait(driver, 30).until(
//            ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
//        textInput.sendKeys("Browserstack\n");
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + "Accept all" + "\")")).click();
        staticDelay();
        percy.screenshot("Second Screenshot");
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" +"I already have an account"+ "\")")).click();
        staticDelay();
        percy.screenshot("Third Screenshot");
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" +"Next"+ "\")")).click();
        staticDelay();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" +"Next"+ "\")")).click();
        staticDelay();
        percy.screenshot("Fourth Screenshot");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Take Second Screenshot post scrolling


        driver.quit();
    }

    private static void staticDelay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
