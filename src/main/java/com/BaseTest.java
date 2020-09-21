package com;


import com.litecart.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class BaseTest implements HasPriority {
    public WebDriver driver;

    public static Properties properties = new Properties();
    protected static ApplicationManager app;

    int checkCounter;
    int checkFrequency;

    @BeforeTest
    public void setUp() throws Exception {
        String configFile = "src/test/resources/config/local.properties";
        properties.load(new FileReader(new File(configFile)));
        app = new ApplicationManager(properties);
        driver = app.getDriver();
        checkCounter = 0;
        checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
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

    private int priority;

    private final String loginpassword = "admin";

    public List<WebElement> findElements(String geoZonesTableRows) {
        List<WebElement> elements = driver.findElements(By.xpath(geoZonesTableRows));
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elements);
        }
        return elements;
    }

    public WebElement findElement(By by) {
        WebElement elem = driver.findElement(by);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
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
