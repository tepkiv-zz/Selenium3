package com.litecart;

import org.openqa.selenium.By;

public class AdminPage extends Page {
    public static final By LEFT_MENU_BOX = By.id("sidebar");

    public static final String main = "//*[@id='main']";
    public static final String breadcrumb = main + "//ul[@class='breadcrumb']";

    public static class SideBar {
        public static final String sidebar = "//*[@id='sidebar']";
        public static final String search = sidebar + "//*[@class='form-control']";
        public static final String menu = sidebar + "//ul[@id='box-apps-menu']";

        public static final String menuLines = menu + "/li";
        // requires menu line name
        public static final String specificMenuLine = menuLines + "//span[@title='%s']";
        public static final String menuSubLine = "./ul/li";
        // requires menu line name + subline name
        public static final String specificMenuSubLine = specificMenuLine + "//ancestor::li//span[contains(text(),'%s')]";
        public static final String lineName = "./a/span";
    }

    public static class CountriesForm {
        public static final String COUNTRIES_FORM = "//form[@name='countries_form']";
        public static final String CONTENT_TABLE = COUNTRIES_FORM + "//table//tbody";
        public static final String CONTENT_TABLE_ROWS = CONTENT_TABLE + "/tr";
        public static final String COUNTRY_COLUMN = "./td/a[1]";
        public static final String ZONES_COLUMN = "./td[@class='text-center']";
        public static final String EDIT_COLUMN = "./td/a[@title='Edit']";
    }
}
