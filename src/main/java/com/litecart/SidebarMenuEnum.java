package com.litecart;

public enum SidebarMenuEnum {
    Appearance("Appearance"),
    Appearance_Template("Template"),
    Appearance_Logotype("Logotype"),
    Catalog("Catalog"),
    Catalog_Catalog("Catalog"),
    Catalog_Attribute_Groups("Attribute Groups"),
    Catalog_Manufacturers("Manufacturers"),
    Catalog_Suppliers("Suppliers"),
    Catalog_Delivery_Statuses("Delivery Statuses"),
    Catalog_Sold_Out_Statuses("Sold Out Statuses"),
    Catalog_Quantity_Units("Quantity Units"),
    Catalog_CSV_Import_Export("CSV Import/Export"),
    Countries("Countries"),
    Currencies("Currencies"),
    Customers("Customers"),
    Customers_Customers_("Customers"),
    Customers_CSV_Import_Export("CSV Import/Export"),
    Customers_Newsletter("Newsletter"),
    Geo_Zones("Geo Zones"),
    Languages("Languages"),
    Languages_Languages("Languages"),
    Languages_Storage_Encoding("Storage Encoding"),
    Modules("Modules"),
    Modules_Customer_Modules("Customer Modules"),
    Modules_Shipping_Modules("Shipping Modules"),
    Modules_Payment_Modules("Payment Modules"),
    Modules_Order_Modules("Order Modules"),
    Modules_Order_Total_Modules("Order Total Modules"),
    Modules_Job_Modules("Job Modules"),
    Orders("Orders"),
    Orders_Orders("Orders"),
    Orders_Order_Statuses("Order Statuses"),
    Pages("Pages"),
    Pages_Pages("Pages"),
    Pages_CSV_Import_Export("CSV Import/Export"),
    Reports("Reports"),
    Reports_Monthly_Sales("Monthly Sales"),
    Reports_Most_Sold_Products("Most Sold Products"),
    Reports_Most_Shopping_Customers("Most Shopping Customers"),
    Settings("Settings"),
    Settings_Store_Info("Store Info"),
    Settings_Defaults("Defaults"),
    Settings_Email("Email"),
    Settings_Listings("Listings"),
    Settings_Customer_Details("Customer Details"),
    Settings_Legal("Legal"),
    Settings_Images("Images"),
    Settings_Checkout("Checkout"),
    Settings_Advanced("Advanced"),
    Settings_Security("Security"),
    Slides("Slides"),
    Tax("Tax"),
    TAX_RATES("Tax Rates"),
    TAX_CLASSES("Tax Classes"),
    TRANSLATIONS("Translations"),
    TRANSLATIONS_SEARCH_TRANSLATIONS("Search Translations"),
    TRANSLATIONS_SCAN_FILES("Scan Files"),
    TRANSLATIONS_CSV_IMPORT_EXPORT("CSV Import/Export"),
    USERS("Users"),
    VQMODS("vQmods");

    private final String menuLineText;

    SidebarMenuEnum(String envUrl) {
        this.menuLineText = envUrl;
    }

    public String getName() {
        return menuLineText;
    }
}
