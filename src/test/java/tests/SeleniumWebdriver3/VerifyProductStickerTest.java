package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.MainPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VerifyProductStickerTest extends BaseTest {
    /**
     * 1. Open litecart
     * 2. Find all products at the page
     * 3. Go through each product and check amount = 1
     */
    @Test
    public void openPageTest() {
        driver.get("http://localhost/litecart");

        assertThat(driver.findElements(By.xpath(MainPage.allProducts))).as("No products found").isNotEmpty();
        for (int i = 0; i < driver.findElements(By.xpath(MainPage.allProducts)).size(); i++) {
            assertThat(driver.findElements(By.xpath(MainPage.allProducts)).get(i).findElements(By.xpath("." + MainPage.sticker)).size())
                    .isEqualTo(1);
        }
    }

}
