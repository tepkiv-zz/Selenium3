package com.litecart;

public class ProductOrderPage {
    public static final String PARENT = "//*[@id='page']";
    public static final String PRODUCT_DESCRIPTION = PARENT + "//article[@id='box-product']";
    public static final String PRODUCT_HEADER = PRODUCT_DESCRIPTION + "//h1";
    public static final String BUY_SECTION = PRODUCT_DESCRIPTION + "//div[@class='buy_now']";
    public static final String ORDER_REGULAR_PRICE = BUY_SECTION + "//*[@class='regular-price']";
    public static final String ORDER_DISCOUNT_PRICE = BUY_SECTION + "//*[@class='campaign-price']";

}
