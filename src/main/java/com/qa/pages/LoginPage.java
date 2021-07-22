package com.qa.pages;

import com.aventstack.extentreports.Status;
import com.qa.BaseTest;
import com.qa.reports.ExtentReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "test-Username")
    private MobileElement usernameTxtFld;
    @AndroidFindBy(accessibility = "test-Password")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(accessibility = "test-LOGIN")
    private MobileElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private MobileElement errorTxt;

    public LoginPage enterUserName(String username) {
        sendKeys(usernameTxtFld, username);
        return this;
    }
    public LoginPage enterUserName(String username, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        utils.log().info("Login username: " + username);
        sendKeys(usernameTxtFld, username);
        return this;
    }
    public LoginPage enterPassword(String password) {
        sendKeys(passwordTxtFld, password);
        return this;
    }
    public LoginPage enterPassword(String password, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        utils.log().info("Login password: " + password);
        sendKeys(passwordTxtFld, password);
        return this;
    }
    public ProductsPage pressLoginBtn() {
        click(loginBtn);
        return new ProductsPage();
    }
    public ProductsPage pressLoginBtn(String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        click(loginBtn);
        return new ProductsPage();
    }
    public ProductsPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return pressLoginBtn();
    }
    public ProductsPage login(String username, String password, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        enterUserName(username);
        enterPassword(password);
        return pressLoginBtn();
    }

    public String getErrTxt() {
        return getAttribute(errorTxt, "text");
    }
    public String getErrTxt(String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        return getAttribute(errorTxt, "text");

    }
}
