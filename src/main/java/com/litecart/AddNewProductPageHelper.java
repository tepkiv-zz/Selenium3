package com.litecart;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewProductPageHelper extends BaseTest {

    public static WebElement getInput(String label) {
        WebElement element = findElement(By.xpath(AddNewProductPage.CONTENT + String.format(AddNewProductPage.INPUT_TEMPLATE, label)));
        element.clear();
        return element;

    }

    public static Select findSelect(String label) {
        return findSelect(By.xpath(AddNewProductPage.CONTENT + String.format(AddNewProductPage.SELECT_TEMPLATE, label)));
    }

    public static boolean openTab(String label) {
        try {
            findElement(By.xpath(String.format(AddNewProductPage.TAB_TEMPLATE, label))).click();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
