package tests.SeleniumWebdriver3.sidebar;

public class MainPage {
    static final String main = "//div[@id='main']";
    static final String allProducts = main + "//article[@class='product-column']";
    static final String allStickers = allProducts + "//div[contains(@class,'sticker')]";
}
