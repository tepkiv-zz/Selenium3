package tests.SeleniumWebdriver3;

import com.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LearnFindElementJavascript extends BaseTest {

    @Test
    public void openPageTest() {
        driver.get("https://webdriver.ru/search/");

        WebElement search = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return document.getElementsByClassName('ais-search-box--input')[0];");
        search.sendKeys("fs");

        assertThat(true).isEqualTo(true);
    }
}
