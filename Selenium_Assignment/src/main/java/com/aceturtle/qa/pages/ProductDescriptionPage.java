package com.aceturtle.qa.pages;

import com.aceturtle.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductDescriptionPage extends TestBase {

    public ProductDescriptionPage() {
        PageFactory.initElements(driver, this);
        ArrayList<String> tabs2 = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        WaitTillElementLoads(driver, searchInputBox);
    }

    @FindBy(id = "search")
    WebElement searchInputBox;

    @FindBy(xpath = "//ul[@id='configurable_swatch_size']/li[contains(@class,'option') and not(contains(@class,'not'))]")
    List<WebElement> availableSize;


    public boolean SelectASize() {

        if (availableSize.size() == 0) {
            return false;
        } else {
            action.moveToElement(availableSize.get(0)).perform();
            availableSize.get(0).click();
            return true;
        }


    }

    public String getPrice() {

//        ExpectedCondition<WebElement> webElementExpectedCondition = ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='regular-price']"));
//        webElementExpectedCondition.
        if (driver.findElements(By.xpath("//span[@class='regular-price']")).size() != 0) {
//            If(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='regular-price']"))) {
            return driver.findElement(By.xpath("//span[@class='regular-price']/span")).getAttribute("value");
        } else if (driver.findElements(By.xpath("//p[@class='was-old-price']")).size() != 0) {
            return driver.findElement(By.xpath("//p[@class='was-old-price']/span[@class='price']")).getText();//getAttribute("value");
        } else {
            return driver.findElement(By.xpath("//p[@class='special-price']/span[@class='price']")).getAttribute("value");
        }

    }

    @FindBy(xpath = "//div[@class='product-name']/h1")
    WebElement productName;

    public String getProductName() {
        return productName.getText();
    }

    @FindBy(xpath = "//button[@class='button btn-cart']")
    WebElement buttonAddToCart;

    public CheckOutPage addToCart() {
        action.moveToElement(buttonAddToCart).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", availableSize.get(0));
        buttonAddToCart.click();
        return new CheckOutPage();
    }
}
