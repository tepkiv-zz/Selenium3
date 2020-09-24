package com.litecart;

import org.openqa.selenium.By;

public class AdminPage extends Page {
    public static final By LEFT_MENU_BOX = By.id("sidebar");

    public static final String breadcrumb = PARENT + "//ul[@class='breadcrumb']";

    public static class SideBar {
        public static final String sidebar = "//*[@id='sidebar']";
        public static final String search = sidebar + "//*[@class='form-control']";
        public static final String menu = sidebar + "//ul[@id='box-apps-menu']";

        public static final String menuLines = menu + "/li";
        // requires menu line name
        public static final String specificMenuLine = menuLines + "//span[@title='%s']//ancestor::a";
        public static final String menuSubLine = "./ul/li";
        // requires menu line name + subline name
        public static final String specificMenuSubLine = specificMenuLine + "//ancestor::li//span[contains(text(),'%s')]";
        public static final String lineName = "./a/span";
    }

    public static class Catalog extends MainPage {
        public static final String CATALOG_HEADER = PARENT + "//div[@class='panel panel-app']";
        public static final String BUTTON_ADD_NEW_PRODUCT = CATALOG_HEADER + "//*[contains(text(),'Add New Product')]//ancestor::a";
    }
}