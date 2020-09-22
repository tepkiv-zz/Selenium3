package com.litecart;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewCategoryPageHelper extends BaseTest {

    public static WebElement getInput(String label) {
        return findElement(By.xpath(AddNewCategoryPage.CONTENT + String.format(AddNewCategoryPage.INPUT_TEMPLATE, label)));
    }

    public static Select findSelect(String label) {
        return findSelect(By.xpath(AddNewCategoryPage.CONTENT + String.format(AddNewCategoryPage.INPUT_TEMPLATE, label)));
    }

    public static boolean openTab(String label) {
        try {
            findElement(By.xpath(String.format(AddNewCategoryPage.TAB_TEMPLATE, label))).click();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
