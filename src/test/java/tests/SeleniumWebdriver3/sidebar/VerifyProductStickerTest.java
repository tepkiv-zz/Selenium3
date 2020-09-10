package tests.SeleniumWebdriver3.sidebar;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VerifyProductStickerTest extends BaseTest {
    /**
     * 1. Open litecart
     * 2. Find all products at the page
     * 3. Go through each product and check amount of stickers >0 and <2
     */
    @Test
    public void openPageTest() {
        driver.get("http://localhost/litecart");

        for (WebElement element : driver.findElements(By.xpath(MainPage.allProducts))) {
            assertThat(element.findElements(By.xpath("." + MainPage.allStickers)).size()).isLessThan(2).isGreaterThan(0);
        }

    }
}
