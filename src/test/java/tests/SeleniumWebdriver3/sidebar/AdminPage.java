package tests.SeleniumWebdriver3.sidebar;

import org.openqa.selenium.By;

public class AdminPage {
    public static final String main = "//*[@id='main']";
    public static final String breadcrumb = main + "//ul[@class='breadcrumb']";

    public static class SideBar {
        public static final String sidebar = "//*[@id='sidebar']";
        public static final String search = sidebar + "//*[@class='form-control']";
        public static final String menu = sidebar + "//ul[@id='box-apps-menu']";

        public static final By menuLines = By.xpath(menu + "/li");
        public static final By menuSubLine = By.xpath("./ul/li");
        public static final By lineName = By.xpath("./a/span");
    }

}
