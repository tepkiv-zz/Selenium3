package com.litecart;

public class EditCountryPage extends EditPage {
   public static final String ZONE_TABLE = "//h2[contains(text(),'Zones')]/../table";
   public static final String ZONE_TABLE_ROWS = ZONE_TABLE + "//tbody/tr";
   public static final String ZONE_TABLE_CODE_COLUMN = ZONE_TABLE_ROWS + "//input[@name[contains(.,'code')]]";

}
