package com;

import com.data.ContactData;
import com.data.GroupData;
import com.helpers.db.HibernateHelper;
import com.helpers.product.ContactHelper;
import com.helpers.product.GroupHelper;
import com.helpers.product.NavigationHelper;
import com.utils.ModifiedSortedList;
import conf.TestProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BrowserController {
    public static ThreadLocal<EventFiringWebDriver> threadLocalDriver = new ThreadLocal<>();
    private static EventFiringWebDriver driver;

    public WebDriverWait wait;

    public String baseUrl;
    private static Properties properties;
    private static WebDriverWait webDriverWait;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private HibernateHelper hibernateHelper;

    private AppModel appModel;

    private List<GroupData> groups;
    private List<ContactData> contacts;

    public BrowserController(Properties properties) {
        BrowserController.properties = properties;
//      appModel = new AppModel();
//      appModel.setGroups(getHibernateHelper().getListOfGroups());
//      appModel.setContacts(getHibernateHelper().getListOfContacts());
    }

    public EventFiringWebDriver getDriver() {
        LocalDesiredCapabilities localDesiredCapabilities = new LocalDesiredCapabilities();
        String browser = properties.getProperty("browser", "googlechrome");
        // Переиспользование браузера тестов в том же потоке
        if (threadLocalDriver.get() != null) {
            driver = threadLocalDriver.get();
            wait = new WebDriverWait(driver, 10);
        }

        if (driver == null) {
            switch (browser) {
                case "ff":
                case "firefox":
                    /**
                     * Solution
                     * Upgrade JDK to recent levels JDK 8u191.
                     * Upgrade Selenium to current levels Version 3.14.0.
                     * Upgrade GeckoDriver to GeckoDriver v0.23.0 level.
                     * GeckoDriver is present in the specified location.
                     * GeckoDriver is having executable permission for non-root users.
                     * Upgrade Firefox version to Firefox v63.0.1 levels.
                     * Clean your Project Workspace through your IDE and Rebuild your project with required dependencies only.
                     * If your base Web Client version is too old, then uninstall it through Revo Uninstaller and install a recent GA and released version of Web Client.
                     * Take a System Reboot.
                     * Execute your Test as a non-root user.
                     * Always invoke driver.quit() within tearDown(){} method to close & destroy the WebDriver and Web Client instances gracefully.
                     */
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("marionette", true);
                    firefoxOptions.setBinary(properties.getProperty(TestProperties.WEBDRIVER_GECKO_BINARY));
                    System.setProperty(TestProperties.GECKO_DRIVER, properties.getProperty(TestProperties.GECKO_DRIVER_PATH));
                    driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOptions));
                    threadLocalDriver.set(driver);
                    driver.manage().window().maximize();
                    break;
                case "chrome":
                case "googlechrome":
                case "gc":
                    File chromeDriver = new File(properties.getProperty(TestProperties.CHROME_DRIVER_PATH));
                    System.setProperty(TestProperties.CHROME_DRIVER, chromeDriver.getAbsolutePath());
                    /*try {
                        driver = new RemoteWebDriver(new URL("http://192.168.0.11:4444/wd/hub"), localDesiredCapabilities.chrome());
                        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }*/
                    driver = new EventFiringWebDriver(new ChromeDriver(localDesiredCapabilities.chrome()));
                    threadLocalDriver.set(driver);
                    break;
                case "mobileApp":
                    DesiredCapabilities androidCapabilities = new DesiredCapabilities();
                    androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2API29");
                    androidCapabilities.setCapability(MobileCapabilityType.APP, new File("src/test/resources/ApiDemos-debug.apk"));
                    try {
                        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                //todo implement
                case "ie":
                case "explorer":
                case "internetexplorer":
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    internetExplorerOptions.setCapability("unexpectedAlertBehaviour", "dismiss");
                    System.setProperty(TestProperties.IE_DRIVER_BINARY, "C:/Program Files/Internet Explorer/iexplore.exe");
                    System.setProperty(TestProperties.IE_DRIVER, properties.getProperty(TestProperties.IE_DRIVER));
                    driver = new EventFiringWebDriver(new InternetExplorerDriver(internetExplorerOptions));
                    threadLocalDriver.set(driver);
                    System.out.println(((HasCapabilities) driver).getCapabilities());
                    break;
                default:
                    throw new Error("Unsupported Browser");
            }

            assert driver != null;
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
            //driver.get(properties.getProperty(TestProperties.BASE_URL));
            //System.out.println(driver.getTitle());
        }
        /*Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));*/
        return new EventFiringWebDriver(driver);
    }

    public GroupHelper getGroupHelper() {
        if (groupHelper == null) {
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        if (contactHelper == null) {
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }

    public HibernateHelper getHibernateHelper() {
        if (hibernateHelper == null) {
            hibernateHelper = new HibernateHelper(this);
        }
        return hibernateHelper;
    }

    public NavigationHelper getNavigationHelper() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public AppModel getAppModel() {
        return appModel;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = new ModifiedSortedList<GroupData>(groups);
    }

    public void setContacts(List<ContactData> contacts) {
        this.contacts = new ModifiedSortedList<ContactData>(contacts);
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


}
