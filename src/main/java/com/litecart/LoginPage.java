package com.litecart;

import org.openqa.selenium.By;

public class LoginPage {
    public static final By USERNAME = By.name("username");
    public static final By PASSWORD = By.name("password");

    public static final By LOGIN = By.xpath("//button[@type='submit']");
}
