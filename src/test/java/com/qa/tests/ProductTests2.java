package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.ui.DeepLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTests2 extends BaseTest {
    @Test
    public void validateProductOnProductsPage() {
        SoftAssert sa = new SoftAssert();
/*        ProductsPage productsPage = new LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
                , loginUsers.getJSONObject("validUser").getString("password"));*/
        DeepLink.OpenAppWith("swaglabs://swag-overview/0,5");
        ProductsPage productsPage = new ProductsPage();
        sa.assertEquals(productsPage.getSLBTitle(), getStrings().get("products_page_slb_title"));
        sa.assertEquals(productsPage.getSLBPrice(), getStrings().get("products_page_slb_price"));
        sa.assertAll();
    }

    @Test
    public void validateProductOnProductDetailsPage() {
/*        ProductsPage productsPage = new LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
                , loginUsers.getJSONObject("validUser").getString("password"));*/
        DeepLink.OpenAppWith("swaglabs://swag-overview/0,5");
        ProductsPage productsPage = new ProductsPage();
        ProductDetailsPage productDetailsPage = productsPage.pressSLBTitle();
        Assert.assertEquals(productDetailsPage.getSLBTitle(), getStrings().get("product_details_page_slb_title"));
    }
}
