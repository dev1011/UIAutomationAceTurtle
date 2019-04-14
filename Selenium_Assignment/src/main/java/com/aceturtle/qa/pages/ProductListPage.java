package com.aceturtle.qa.pages;

import com.aceturtle.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductListPage extends TestBase {


    public ProductListPage() {
        PageFactory.initElements(driver, this);
        WaitTillElementLoads(driver, searchInputBox);
    }

    // Get all the products
    @FindBy(xpath = "//li[@class='item last']")
    List<WebElement> products;

    @FindBy(id = "search")
    WebElement searchInputBox;

    // Click on product based on index and return ProductDescriptionPage
    public ProductDescriptionPage clickOnProduct(int index) {
       products.get(index).click();
       return new ProductDescriptionPage();
    }


}
