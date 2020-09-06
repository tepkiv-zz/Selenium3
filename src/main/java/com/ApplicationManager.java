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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    public String baseUrl;
    private static Properties properties;
    private static WebDriver driver;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private HibernateHelper hibernateHelper;

    private AppModel appModel;

    private List<GroupData> groups;
    private List<ContactData> contacts;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
//      appModel = new AppModel();
//      appModel.setGroups(getHibernateHelper().getListOfGroups());
//      appModel.setContacts(getHibernateHelper().getListOfContacts());
    }

    public void stop() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        LocalDesiredCapabilities localDesiredCapabilities = new LocalDesiredCapabilities();
        String browser = properties.getProperty("browser", "googlechrome");
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
                    firefoxOptions.setBinary(properties.getProperty(TestProperties.BINARY_PATH));
                    System.setProperty(TestProperties.GECKO_DRIVER, properties.getProperty(TestProperties.GECKO_DRIVER_PATH));
                    driver = new FirefoxDriver(firefoxOptions);
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
                    driver = new ChromeDriver(localDesiredCapabilities.chrome());
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
                    break;
                default:
                    throw new Error("Unsupported Browser");
            }

            assert driver != null;
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //driver.get(properties.getProperty(TestProperties.BASE_URL));
            //System.out.println(driver.getTitle());

        }
        return driver;
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

    public String getProperty(String key) {
       return  properties.getProperty(key);
    }


}
