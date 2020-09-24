package com;


import com.litecart.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class BaseTest implements HasPriority {
    public static WebDriver driver;
    public static WebDriverWait wait;
    protected static BrowserController app;
    public static Properties properties = new Properties();
    private final String loginpassword = "admin";

    int checkCounter;
    int checkFrequency;
    public String RESOURCES_FOLDER = "src/test/resources/";
    private int priority;

    public static Select findSelect(By xpath) {
        Select select = new Select(findElement(xpath));
        select.deselectAll();
        return select;
    }

    public void open(String s) {
        driver.get(s);
    }

    private void open() {
        driver.get("google.com");
    }

    @AfterTest
    public void tearDown() throws Exception {
        //driver.quit();
    }

    public static String generateRandomString() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 0) {
            return "";
        } else {
            return "tests.test" + rnd.nextInt();
        }
    }

    public static WebElement findElement(By by) {
        WebElement elem = app.getDriver().findElement(by);
        // draw a border around the found element
        if (app.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) app.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    @BeforeTest
    public void setUp() throws Exception {
        String configFile = "src/test/resources/config/local.properties";
        properties.load(new FileReader(new File(configFile)));
        app = new BrowserController(properties);
        driver = app.getDriver();
        wait = app.getWait();
        checkCounter = 0;
        checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
    }

    public List<WebElement> findElements(String geoZonesTableRows) {
        List<WebElement> elements = app.getDriver().findElements(By.xpath(geoZonesTableRows));
        if (app.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) app.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elements);
        }
        return elements;
    }

    public boolean ttc() {
        checkCounter++;
        if (checkCounter > checkFrequency) {
            checkCounter = 0;
            return true;

        } else {
            return false;
        }
    }

    public Double getFontSize(WebElement element) {
        return Double.parseDouble(element.getCssValue("font-size").replace("px", ""));
    }

    protected void openAdmin() {
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(LoginPage.USERNAME).sendKeys(loginpassword);
        driver.findElement(LoginPage.PASSWORD).sendKeys(loginpassword);

        driver.findElement(LoginPage.LOGIN).click();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int _priority) {
        this.priority = _priority;
    }

}
