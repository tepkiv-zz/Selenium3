package com;

import com.helpers.db.HibernateHelper;
import com.helpers.product.ContactHelper;
import com.helpers.product.GroupHelper;
import com.helpers.product.NavigationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    public String baseUrl;
    private WebDriver driver;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private HibernateHelper hibernateHelper;

    private Properties properties;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
    }

    public void stop() {
        driver.quit();
    }

    public NavigationHelper navigateTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
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

    public WebDriver getDriver() {
        String browser = properties.getProperty("browser");
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
                //System.setProperty("webdriver.firefox.marionette", "C:\\repo\\exercises\\test_excercises_new\\src\\test\\resources\\geckodriver.exe");
                //System.setProperty("webdriver.gecko.driver", "C:\\repo\\exercises\\test_excercises_new\\src\\test\\resources\\geckodriver.exe");
            } else if ("googlechrome".equals(browser)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                File chromeDriver = new File("src\\test\\resources\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
                driver = new ChromeDriver(options);
            } else {
                throw new Error("Unsupported Browser");
            }
            baseUrl = properties.getProperty("baseUrl");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //navigationHelper = new NavigationHelper(this);
            //groupHelper = new GroupHelper(this);
            //contactHelper = new ContactHelper(this);
            driver.get(baseUrl);
        }
        return driver;
    }


}
