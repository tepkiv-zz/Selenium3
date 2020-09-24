package com.litecart;

import org.openqa.selenium.By;

public class CheckoutPage extends Page {
    public static final By checkoutTable = By.xpath("//table[contains(@class,'items table')]//tr[@class='item']");
    public static final By cart = By.xpath("//div[@id='cart']");
    public static final String checkoutBag = "//div[contains(@class,'badge quantity')]";

}
