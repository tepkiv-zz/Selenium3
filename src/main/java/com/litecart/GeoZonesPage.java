package com.litecart;

public class GeoZonesPage extends Page {
    public static final String GEO_ZONES_FORM = "//form[@name='geo_zones_form']";
    public static final String GEO_ZONES_TABLE = GEO_ZONES_FORM + "//table//tbody";
    public static final String GEO_ZONES_TABLE_ROWS = GEO_ZONES_TABLE + "/tr";
    public static final String NAME_COLUMN = "//td[3]/a";

}
