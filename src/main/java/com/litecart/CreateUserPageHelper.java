package com.litecart;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateUserPageHelper extends BaseTest {

    public WebElement getInputField(String label) {
        return findElement(By.xpath(CreateUserPage.CUSTOMER_FORM + String.format(CreateUserPage.INPUT_TEMPLATE, label)));
    }

    public Select getSelectField(String label) {
        return new Select(findElement(By.xpath(CreateUserPage.CUSTOMER_FORM + String.format(CreateUserPage.SELECT_TEMPLATE, label))));
    }

}
