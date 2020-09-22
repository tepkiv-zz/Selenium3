package com.litecart;

public class AddNewProductPage extends Page {
    public static final String CONTENT = PARENT + "//*[@id='content']";
    public static final String TABS = PARENT + "//*[@class='nav nav-tabs']";
    public static final String IMAGE_INPUT = CONTENT + "//input[@type='file']";

    public static final String INPUT_TEMPLATE = "//label[contains(text(),'%s')]/..//input";
    public static final String SELECT_TEMPLATE = "//label[contains(text(),'%s')]/..//select";

    public static final String TEXTAREA_DESCRIPTION = CONTENT + "//textarea[@name='description[en]']";
    public static final String TEXTAREA_TECHNICAL_DATA = CONTENT + "//textarea[@name='technical_data[en]']";
    public static final String INPUT_PRICE_USD = CONTENT + "//input[@name='prices[USD]']";
    public static final String INPUT_PRICE_EURO = CONTENT + "//input[@name='prices[EUR]']";
    public static final String BUTTON_SAVE = CONTENT + "//*[@value='Save']";
    public static final String TAB_TEMPLATE = TABS + "//a[contains(text(),'%s')]";
}
