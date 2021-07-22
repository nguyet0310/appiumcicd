package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;

    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        InputStream datais=null;
        try{
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if (datais != null){
                datais.close();
            }
        }
        closeApp();
        launchApp();
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        utils.log().info("\n" + "*************** " + m.getName() + " *************" + "\n");
    }

    @AfterMethod
    public synchronized void afterMethod() {

    }

    @Test
    public void invalidUserName() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"),"Enter username");
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"), "Enter password");
        loginPage.pressLoginBtn("press Login button");
        String actualErrTxt = loginPage.getErrTxt("Get text error");
        String expectedErrTxt = getStrings().get("err_invalid_user_or_password");
//        utils.log().info("actual error text is: " + actualErrTxt + "\n" + "expected err txt is: " + expectedErrTxt);
        Assert.assertEquals(actualErrTxt + "test1", expectedErrTxt);
    }

    @Test
    public void invalidPassword() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();
        String actualErrTxt = loginPage.getErrTxt();
        String expectedErrTxt = getStrings().get("err_invalid_user_or_password");
//        utils.log().info("actual error text is: " + actualErrTxt + "\n" + "expected err txt is: " + expectedErrTxt);
        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.pressLoginBtn();
        String actualProductTitle = productsPage.getTitle();
        String expectedProductTitle = getStrings().get("product_title");
//        utils.log().info("actual title is: " + actualProductTitle + "\n" + "expected product title is: " + expectedProductTitle);
        Assert.assertEquals(actualProductTitle, expectedProductTitle);
    }
}
