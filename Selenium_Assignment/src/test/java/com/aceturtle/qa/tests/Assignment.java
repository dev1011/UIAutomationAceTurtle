package com.aceturtle.qa.tests;

import com.aceturtle.qa.base.TestBase;
import com.aceturtle.qa.pages.CheckOutPage;
import com.aceturtle.qa.pages.HomePage;
import com.aceturtle.qa.pages.ProductDescriptionPage;
import com.aceturtle.qa.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment extends TestBase {

    HomePage homePage;
    ProductListPage productListPage;
    ProductDescriptionPage productDescriptionPage;
    CheckOutPage checkOutPage;

    public Assignment() {
        super();


    }

    @Test
    public void test() throws InterruptedException {

        System.out.println("Test Started...!!!");
        homePage = new HomePage();

        // Get title and validate
        String title = driver.getTitle();
        System.out.println("Title : " + title);
        Assert.assertEquals(title, "PUMA.COM | Forever Faster");

        // Select Men/Shoes/Running and initliaze productListPage
        productListPage = homePage.selectMenuItem(HomePage.MainMenuItems.MEN, HomePage.SubMenuItem1.SHOES, HomePage.SubMenuItem2.RUNNING);

        // Click on 2nd product from the list
        productDescriptionPage = productListPage.clickOnProduct(1);

        // Select an available size, only sizes which are available will be considered, if no size if available, false will be returned
        boolean sizeAvailable = productDescriptionPage.SelectASize();

        // Test will fail here, if no size is available
        Assert.assertEquals(true, sizeAvailable);

        // Get product details from product description page
        String price = productDescriptionPage.getPrice();
        System.out.println("Price : " + price);
        String productName = productDescriptionPage.getProductName();
        System.out.println("Product Name : " + productName);
        
        // Click on checkout Button and initialize checkOutPage object
        checkOutPage = productDescriptionPage.addToCart();

        // Get product details from checkOut Page
        String priceFromCart = checkOutPage.getProductPrice(0);

        String nameFromCart = checkOutPage.getProductName(0);

        // Validate product details from product description and check out page
        Assert.assertEquals(price, priceFromCart);
        Assert.assertEquals(productName, nameFromCart);

        driver.close();
        driver.quit();
        System.out.println("Test Finished...!!!");
    }


}
