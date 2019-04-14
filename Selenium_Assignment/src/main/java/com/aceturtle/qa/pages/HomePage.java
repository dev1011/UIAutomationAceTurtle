package com.aceturtle.qa.pages;

import com.aceturtle.qa.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class HomePage extends TestBase {

    public HomePage() {
        PageFactory.initElements(driver, this);
        WaitTillElementLoads(driver, searchInputBox);
    }

    @FindBy(id = "search")
    WebElement searchInputBox;


    @FindBy(xpath = "//input[@name='notnow']")
    WebElement notNowButton;


    public void clickOnNotNowButton() throws InterruptedException {

        Thread.sleep(5000);


        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window

// Now you are in the popup window, perform necessary actions here
        Alert alert = driver.switchTo().alert();
        String print = alert.getText();
        System.out.println(print);
        alert.dismiss();

        driver.switchTo().window(parentWindowHandler);  // switch back to parent window

        Set<String> windowHandles = driver.getWindowHandles();

//        if (driver.findElements(By.xpath("//input[@name='notnow']")).size() != 0) {
//            notNowButton.click();
//        }

    }

    public WebElement getMainMenuItem(String mainMenu) {
        return driver.findElement(
                By.xpath("//ul[@class='digimeg-group digimeg-main-nav']/li/a[contains(text(),'" + mainMenu + "')]"));
    }

    public WebElement getSubMenuItem(String mainMenu, String subMenuItem1, String subMenuItem2) {
        return  driver.findElement(By.xpath("//li[@id='" + mainMenu.toLowerCase()
                + "-subnav']//a[text()='" + subMenuItem1 + "']//parent::p//parent::li/following-sibling::li/a[text()='"
                        + subMenuItem2 + "']"));
    }

    public ProductListPage selectMenuItem(String mainMenu, String subMenuItem1, String subMenuItem2) {

        // Mouse Hover the main menu items
        WebElement mainMenuItem = getMainMenuItem(mainMenu);
        WaitTillElementLoads(driver, mainMenuItem);
        action.moveToElement(mainMenuItem).perform();

        //Click on submenuItem
        WebElement menuItem = getSubMenuItem(mainMenu, subMenuItem1, subMenuItem2);
        WaitTillElementLoads(driver, menuItem);
        menuItem.click();

        // return ProductListPage object
        return new ProductListPage();
    }


    public static class MainMenuItems {
        public static String MEN = "MEN";
        public static String WOMEN = "WOMEN";
    }

    public static class SubMenuItem1 {
        public static String FEATURED = "Featured";
        public static String SHOES = "Shoes";
        public static String CLOTHING = "Clothing";
        public static String ACCESSORIES = "Accessories";
    }

    public static class SubMenuItem2 {
        public static String RUNNING = "Running";
        public static String TRAINING = "Training";
        public static String MOTORSPORT = "Motorsport";
        public static String FOOTBALL = "Football";
    }

}
