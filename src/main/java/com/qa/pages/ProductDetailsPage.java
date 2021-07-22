package com.qa.pages;

import com.qa.MenuPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private MobileElement SLBTitle;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private MobileElement SLBTxt;
    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private MobileElement backToProductsBtn;
    @AndroidFindBy(accessibility = "test-Price")
    private MobileElement SLBTPrice;

    public String getSLBTitle() {
        return getAttribute(SLBTitle, "text");
    }

    public String getSLBPrice() {
        return getAttribute(SLBTPrice, "text");
    }

    public String getSLBTxt() {
        return getAttribute(SLBTxt, "text");
    }

    public ProductDetailsPage scrollToSLBTPrice() {
        scrollToElement();
        return this;
    }

    public ProductsPage pressBackToProductsBtn() {
        utils.log().info("press Back to product btn");
        click(backToProductsBtn);
        return new ProductsPage();
    }
}
