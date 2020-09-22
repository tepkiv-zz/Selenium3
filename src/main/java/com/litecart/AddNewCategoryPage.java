package com.litecart;

public class AddNewCategoryPage extends Page {
    public static final String CONTENT = PARENT + "//*[@id='content']";
    public static final String TABS = PARENT + "//*[@class='nav nav-tabs']";
    public static final String IMAGE_SECTION = CONTENT + "//div[@id='image']";

    public static final String INPUT_TEMPLATE = "//label[contains(text(),'%s')]/..//input";
    public static final String SELECT_TEMPLATE = "//label[contains(text(),'%s')]/..//select";
    public static final String TAB_TEMPLATE = TABS + "//a[contains(text(),'%s')]";
}
