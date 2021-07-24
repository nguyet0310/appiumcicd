package com.qa;

import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReport;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<Properties> props = new ThreadLocal<>();
    protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
    protected static ThreadLocal<String> platform = new ThreadLocal<>();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<>();
    protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    public TestUtils utils = new TestUtils();

    private static AppiumDriverLocalService server;

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String datetime2) {
        dateTime.set(datetime2);
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform1) {
        platform.set(platform1);
    }

    public Properties getProps() {
        return props.get();
    }

    public void setProps(Properties props2) {
        props.set(props2);
    }

    public HashMap<String, String> getStrings() {
        return strings.get();
    }

    public void setStrings(HashMap<String, String> strings1) {
        strings.set(strings1);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @BeforeSuite
    public void beforeSuite() {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
//        server = getAppiumServerDefault();
        server = getAppiumService2();
        if(!server.isRunning()){
            server.start();
            server.clearOutPutStreams();
            utils.log().info("Server is starting");
        }else {
            utils.log().info("Server is already running");
        }

    }

    @AfterSuite
    public void afterSuite() {
        utils.log().info("Server is stopping");
        server.stop();
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    // TODO: not complete yet, wait until work with MAC machine
    public AppiumDriverLocalService getAppiumService2() {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Windows\\System32\\OpenSSH\\;C:\\Program Files\\Microsoft SQL Server\\Client SDK\\ODBC\\170\\Tools\\Binn\\;C:\\Program Files (x86)\\Microsoft SQL Server\\150\\Tools\\Binn\\;C:\\Program Files\\Microsoft SQL Server\\150\\Tools\\Binn\\;C:\\Program Files\\Microsoft SQL Server\\150\\DTS\\Binn\\;C:\\Program Files (x86)\\Microsoft SQL Server\\150\\DTS\\Binn\\;C:\\Program Files\\Azure Data Studio\\bin;C:\\Program Files\\dotnet\\;C:\\Program Files\\Microsoft SQL Server\\130\\Tools\\Binn\\;C:\\Program Files\\PuTTY\\;C:\\Program Files\\nodejs\\;C:\\Program Files\\Git\\cmd;C:\\apache-maven-3.8.1\\bin;C:\\Users\\nguyetnm\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Windows\\System32\\cmd.exe;C:\\Program Files\\PostgreSQL\\11\\bin;C:\\Program Files\\Java\\jdk1.8.0_281\\bin;C:\\Users\\nguyetnm\\.dotnet\\tools;C:\\Users\\nguyetnm\\AppData\\Roaming\\npm;C:\\Users\\nguyetnm\\AppData\\Local\\Android\\Sdk\\platform-tools;C:\\Users\\nguyetnm\\AppData\\Local\\Android\\Sdk\\tools;C:\\Users\\nguyetnm\\AppData\\Local\\Android\\Sdk\\tools\\bin;C:\\tmp\\apache-maven-3.8.1\\bin;C:\\Users\\nguyetnm\\Downloads\\Compressed\\ffmpeg-4.4-full_build\\bin;C:\\Users\\nguyetnm\\Downloads\\Compressed\\Bento4-SDK-1-6-0-638.x86_64-microsoft-win32\\bin;C:\\Users\\nguyetnm\\Downloads\\Compressed\\aria2-1.35.0-win-64bit-build1;C:\\Users\\nguyetnm\\AppData\\Local\\GitHubDesktop\\bin;C:\\Users\\nguyetnm\\Downloads\\Compressed\\curl-7.77.0-win64-mingw\\bin;C:\\Users\\nguyetnm\\Downloads\\Compressed\\gradle-5.6.4\\bin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "C:\\Users\\nguyetnm\\AppData\\Local\\Android\\Sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\nguyetnm\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File("ServerLogs/server.log")));
    }
    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, @Optional("androidOnly") String platformVersion, String deviceName) throws Exception {
        InputStream inputStream = null;
        InputStream stringsis = null;
        setDateTime(utils.getDateTime());
        setPlatform(platformName);
        setDeviceName(deviceName);
        Properties props = new Properties();
        String sauce_username = "moon310";
        String access_key = "b6ae4594-fd76-46ec-a4ca-a6a204a81e05";
        String strFile = "logs" + File.separator + getPlatform() + "_" + utils.removeSpecialString(getDeviceName());
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
        ThreadContext.put("ROUTINGKEY", strFile);
        utils.log().info("log path: " + strFile);
        AppiumDriver driver = null;
        try {
            String propFileName = "config.properties";
            String xmlFileName = "strings/strings.xml";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            setProps(props);
            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            utils = new TestUtils();
            setStrings(utils.parseStringXML(stringsis));

            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            switch (platformName) {
                case "Android1":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
//            Start emulator
                    caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                    caps.setCapability("avd", "Pixel_3");
                    caps.setCapability("avdLaunchTimeout", 18000);
                    //This to open app already installed in the device
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    // This to install app
//            URL appURL = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
//            caps.setCapability(MobileCapabilityType.APP, appURL);
                    URL url = new URL(props.getProperty("appiumURL"));
                    driver = new AndroidDriver(url, caps);
                    break;
                case "Android":
                    caps.setCapability("idleTimeout", "90");
                    caps.setCapability("noReset", "true");
                    caps.setCapability("newCommandTimeout", "90");
                    caps.setCapability("appWaitActivity", props.getProperty("androidAppWaitActivity"));
                    caps.setCapability("name", "test cloud device 1");
                    caps.setCapability("app",
                            "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    driver = new AndroidDriver<>(
                            new URL("https://" + sauce_username + ":" +
                                    access_key + "@ondemand.us-west-1.saucelabs.com/wd/hub"),
                            caps);
                    break;
                case "iOS":
                    // Set your access credentials
                    caps.setCapability("browserstack.user", "luoianmon_UKZL6O");
                    caps.setCapability("browserstack.key", "7bdgDtVxR58bVxLhiSWc");

                    // Set URL of the application under test
                    caps.setCapability("app", "bs://7e45661f729ad2be9582290adf804faca71b8664");

                    // Specify device and os_version for testing
                    caps.setCapability("device", "iPhone XS");
                    caps.setCapability("os_version", "12");

                    // Set other BrowserStack capabilities
                    caps.setCapability("project", "First Java Project");
                    caps.setCapability("build", "Java iOS");
                    caps.setCapability("name", "1. Test iOS application from Windows");
                    // Initialise the remote Webdriver using BrowserStack remote URL
                    // and desired capabilities defined above
                    driver = new IOSDriver<IOSElement>(
                            new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);
                    break;
            }
            setDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stringsis != null) {
                stringsis.close();
            }
        }
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }
    public void click(MobileElement e, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        waitForVisibility(e);
        e.click();
    }
    public void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }
    public void sendKeys(MobileElement e, String txt, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        waitForVisibility(e);
        e.sendKeys(txt);
    }
    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    public String getAttribute(MobileElement e, String attribute, String msg) {
        utils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public void closeApp() {
        getDriver().closeApp();
    }

    public void launchApp() {
        ((InteractsWithApps) getDriver()).launchApp();
    }

    public MobileElement scrollToElement() {
        return (MobileElement) ((FindsByAndroidUIAutomator) getDriver())
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));");

    }

    @AfterTest
    public void afterTest() {
        getDriver().quit();
    }
}
