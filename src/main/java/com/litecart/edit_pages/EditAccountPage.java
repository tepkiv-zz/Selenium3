package com.litecart.edit_pages;

public class EditAccountPage {
    public static final String ACCOUNT_INFORMATION_PARENT = "//div[@id='page']";
    public static final String ACCOUNT_INFORMATION_CUSTOMER_SERVICE_SECTION = ACCOUNT_INFORMATION_PARENT + "//section[@id='box-information-links']";
    public static final String ACCOUNT_INFORMATION_ACCOUNT_SECTION = ACCOUNT_INFORMATION_PARENT + "//section[@id='box-account']";
    public static final String LOGOUT_BUTTON_INFORMATION_PARENT = ACCOUNT_INFORMATION_ACCOUNT_SECTION + "//li//a[contains(text(),'Logout')]";
}
