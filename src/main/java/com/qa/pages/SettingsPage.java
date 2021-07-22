package com.qa.pages;

import com.aventstack.extentreports.Status;
import com.qa.BaseTest;
import com.qa.reports.ExtentReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BaseTest {
    @AndroidFindBy (accessibility = "test-LOGOUT") private MobileElement logoutBtn;

    public LoginPage pressLogoutBtn(){
        click(logoutBtn);
        return new LoginPage();
    }
    public LoginPage pressLogoutBtn(String msg){
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        click(logoutBtn);
        return new LoginPage();
    }
}
