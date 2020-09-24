package com.litecart;

public class CountriesPage {
    public static final String COUNTRIES_FORM = "//form[@name='countries_form']";
    public static final String CONTENT_TABLE = COUNTRIES_FORM + "//table//tbody";
    public static final String CONTENT_TABLE_ROWS = CONTENT_TABLE + "/tr";
    public static final String COUNTRY_COLUMN = "./td/a[1]";
    public static final String ZONES_COLUMN = "./td[@class='text-center']";
    public static final String EDIT_COLUMN = "./td/a[@title='Edit']";
}
