package com.litecart;

public class MainPage extends Page {
    public static final String main = "//*[@id='page']";
    public static final String NAVIGATION_PANEL = main + "//nav[@id='site-menu']";
    public static final String SIGNIN_NAVIGATION_DROPDOWN = NAVIGATION_PANEL + "//li[contains(@class,'account dropdown')]";

    public static final String allProducts = main + "//article[@class='product-column']";
    public static final String sticker = "//div[contains(@class,'sticker')]";

    public static final String COMPAIGN_SECTION = main + "//*[@id='box-campaign-products']";
    public static final String COMPAIGN_SECTION_LIST_OF_PRODUCTS = COMPAIGN_SECTION + "//div[@class='listing products']";
    public static final String PRICE_WRAPPER = "//div[@class='price-wrapper']";
    public static final String REGULAR_PRICE = PRICE_WRAPPER + "//*[@class='regular-price']";
    public static final String DISCOUNT_WRAPPER = PRICE_WRAPPER + "//*[@class='campaign-price']";

    public static final String COMPAIGN_SECTION_FIRST_PRODUCT = COMPAIGN_SECTION_LIST_OF_PRODUCTS + "//*[@class='product-column'][1]";
    public static final String COMPAIGN_SECTION_FIRST_PRODUCT_NAME = COMPAIGN_SECTION_FIRST_PRODUCT + "//*[@class='name']";

    public class SignInDropdown {
        public static final String PARENT = "//ul[@class='dropdown-menu']";
        public static final String INPUT_EMAIL = PARENT + "//input[@type='email']";
        public static final String INPUT_PASSWORD = PARENT + "//input[@type='password']";
        public static final String BUTTON_SIGN_IN = PARENT + "//button[@type='submit']";
    }

    public class UserDropDown {
        public static final String PARENT = "//ul[@class='dropdown-menu']";
        public static final String LOGOUT = PARENT + "//li//a[contains(text(),'Logout')]";
    }
}
