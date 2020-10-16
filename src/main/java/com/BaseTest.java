package com;


import com.google.common.io.Files;
import com.litecart.LoginPage;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class BaseTest implements HasPriority {
    public static final String RESOURCES_FOLDER = "src/test/resources/";
    public static final String configFile = "src/test/resources/config/local.properties";
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class.getSimpleName());
    public static WebDriverWait wait;
    public static Properties properties = new Properties();
    public static EventFiringWebDriver driver;
    public static BrowserController browserController;

    private int priority;
    private int checkCounter;
    private int checkFrequency;

    public static String generateRandomString() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 0) {
            return "";
        } else {
            return "tests.test" + rnd.nextInt();
        }
    }

    public static WebElement findElement(By by) {
        WebElement elem = browserController.getDriver().findElement(by);
        // draw a border around the found element
        if (browserController.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browserController.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    public static Select findSelect(By xpath) {
        Select select = new Select(findElement(xpath));
        select.deselectAll();
        return select;
    }


    public void waitElementPresence(By xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    public void waitElementInvisibility(By xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }


    @BeforeTest
    public void setUp() throws Exception {
        properties.load(new FileReader(new File(configFile)));
        browserController = new BrowserController(properties);
        driver = browserController.getDriver();
        wait = browserController.getWait();
        driver.register(new EventListener());

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        int port = proxy.getPort(); // get the JVM-assigned port
        // Selenium or HTTP client configuration goes here

        checkCounter = 0;
        checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int _priority) {
        this.priority = _priority;
    }

    public List<WebElement> findElements(String stringXpath) {
        List<WebElement> elements = browserController.getDriver().findElements(By.xpath(stringXpath));
        if (browserController.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browserController.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elements);
        }
        return elements;
    }

    protected void openAdmin() {
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(LoginPage.USERNAME).sendKeys("admin");
        driver.findElement(LoginPage.PASSWORD).sendKeys("admin");

        driver.findElement(LoginPage.LOGIN).click();
    }

    @AfterTest
    public void tearDown() throws Exception {
        //driver.quit();
    }

    public void open(String s) {
        driver.get(s);
    }

    private void open() {
        driver.get("google.com");
    }

    public static class EventListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.debug(by.toString());
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.debug(by.toString() + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.info(throwable.getMessage());
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File("screen" + System.currentTimeMillis() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

}
