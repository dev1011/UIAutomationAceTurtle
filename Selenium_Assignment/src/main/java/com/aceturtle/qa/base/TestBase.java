package com.aceturtle.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
public  static Actions action;

    public TestBase() {

        try {
            StringBuilder configfilePath = new StringBuilder();
            configfilePath.append(System.getProperty("user.dir"));
            configfilePath.append("/src/main/java/com/aceturtle/qa/config/config.properties");
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(configfilePath.toString());
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Config Properties File not found.. Cannot proceed.. !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setup(Method method) throws Exception {
        initialization();
    }

    public static void initialization() {
        String appUrl = prop.getProperty("url");

        String chromeDrivePath = System.getProperty("user.dir") + "\\" + prop.getProperty("chromeDriverPath");
        System.setProperty("webdriver.chrome.driver", chromeDrivePath);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();


        driver.get(appUrl);
        action = new Actions(driver);
    }

    public void WaitTillElementLoads(WebDriver driver, WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 40, 3000);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));

    }

}
