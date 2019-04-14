package com.aceturtle.qa.pages;

import com.aceturtle.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends TestBase {

    public CheckOutPage() {
        PageFactory.initElements(driver, this);
        WaitTillElementLoads(driver, searchInputBox);
    }


    @FindBy(id = "search")
    WebElement searchInputBox;

    @FindBy(xpath = "//td[@class='product-cart-price']//span[@class='price']")
    List<WebElement> productPrice;

    public String getProductPrice(int productIndex) {
        return productPrice.get(productIndex).getText();
    }

    @FindBy(xpath = "//h2[@class='product-name']/a")
    List<WebElement> prouctName;

    public String getProductName(int productIndex) {
        return prouctName.get(productIndex).getText();
    }

}
