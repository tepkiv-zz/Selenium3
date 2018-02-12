package com.helpers;

import com.ApplicationManager;
import com.helpers.HelperBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriverHelperBase extends HelperBase {

    public WebDriver driver;
    public boolean acceptNextAlert = true;
    private WebDriverWait wait;

    public WebDriverHelperBase(ApplicationManager manager) {
        super(manager);
        this.driver = manager.getDriver();
        wait = new WebDriverWait(driver,10);
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void type(By locator, String text) {
        if (text != null) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }

    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void select(By locator, String text) {

        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }

    protected void selectGroupByIndex(int index) {
        click(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}
