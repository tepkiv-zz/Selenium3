package com.litecart;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateUserPageHelper extends BaseTest {

    private final WebDriver driver;

    public CreateUserPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInputField(String label) {
        return driver.findElement(By.xpath(CreateUserPage.CUSTOMER_FORM + String.format(CreateUserPage.INPUT_TEMPLATE, label)));
    }

    public Select getSelectField(String label) {
        return new Select(driver.findElement(By.xpath(CreateUserPage.CUSTOMER_FORM + String.format(CreateUserPage.SELECT_TEMPLATE, label))));
    }

}
