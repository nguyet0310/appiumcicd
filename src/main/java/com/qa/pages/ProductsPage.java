package com.qa.pages;

import com.aventstack.extentreports.Status;
import com.qa.MenuPage;
import com.qa.reports.ExtentReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private MobileElement productTitleTxt;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private MobileElement SLBTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private MobileElement SLBPrice;

    public String getTitle() {
        return getAttribute(productTitleTxt, "text");
    }

    public String getSLBTitle() {
        return getAttribute(SLBTitle, "text");
    }

    public String getSLBPrice() {
        return getAttribute(SLBPrice, "text");
    }

    public ProductDetailsPage pressSLBTitle() {
        click(SLBTitle);
        return new ProductDetailsPage();
    }
    public ProductDetailsPage pressSLBTitle(String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        click(SLBTitle);
        return new ProductDetailsPage();
    }
}
