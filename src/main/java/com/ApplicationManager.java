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
        appModel = new AppModel();
        appModel.setGroups(getHibernateHelper().getListOfGroups());
        //  appModel.setContacts(getHibernateHelper().getListOfContacts());
    }

    public void stop() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        LocalDesiredCapabilities localDesiredCapabilities = new LocalDesiredCapabilities();
        String browser = properties.getProperty("browser");
        if (driver == null) {
            switch (browser) {
                case "ff":
                case "firefox":
                    System.setProperty(TestProperties.GECKO_DRIVER, properties.getProperty(TestProperties.GECKO_DRIVER_PATH));
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    //System.setProperty("webdriver.firefox.marionette", "");
                    break;
                case "chrome":
                case "googlechrome":
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
                    break;
                //todo implement
                case "internetexplorer":
                    break;
                default:
                    throw new Error("Unsupported Browser");
            }

            assert driver != null;
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(properties.getProperty(TestProperties.BASE_URL));
            System.out.println(driver.getTitle());

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
