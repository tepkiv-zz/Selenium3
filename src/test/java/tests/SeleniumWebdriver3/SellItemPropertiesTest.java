package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.ProductOrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import static com.litecart.MainPage.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SellItemPropertiesTest extends BaseTest {

    public static final String GRAY_RGB = "rgb(51, 51, 51)";
    public static final String RED_RGB = "rgb(204, 0, 0)";
    String redHex = "#cc0000";
    String grayHex = "#333333";

    @Test
    public void openPageTest() {
        driver.get("http://localhost/litecart");

        WebElement elementDiscountPrice = findElement(By.xpath(COMPAIGN_SECTION_FIRST_PRODUCT + DISCOUNT_WRAPPER));
        WebElement elementRegularPrice = findElement(By.xpath(COMPAIGN_SECTION_FIRST_PRODUCT + REGULAR_PRICE));

        String mainPageProductName = findElement(By.xpath(COMPAIGN_SECTION_FIRST_PRODUCT_NAME)).getText();
        String mainPageRegularPrice = elementRegularPrice.getText();
        String mainPageDiscountPrice = elementDiscountPrice.getText();

        // в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        String color = elementRegularPrice.getCssValue("color");
        assertThat(getHexColor(color)).isEqualTo(grayHex);
        String cssValue = elementRegularPrice.getCssValue("text-decoration-line");
        if (properties.getProperty("browser").equals("ie")) {
            cssValue = elementRegularPrice.getCssValue("text-decoration");
        }
        assertThat(cssValue).isEqualTo("line-through");
        // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        color = elementDiscountPrice.getCssValue("color");
        assertThat(getHexColor(color)).isEqualTo(redHex);
        assertThat(elementDiscountPrice.getCssValue("font-weight")).isEqualTo("700");
        // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        assertThat(getFontSize(elementDiscountPrice))
                .isGreaterThan(getFontSize(elementRegularPrice));

        // Open product details page
        findElement(By.xpath(COMPAIGN_SECTION_FIRST_PRODUCT)).click();
        // a) на главной странице и на странице товара совпадает текст названия товара
        assertThat(findElement(By.xpath(ProductOrderPage.PRODUCT_HEADER)).getText())
                .isEqualTo(mainPageProductName);
        // б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        assertThat(findElement(By.xpath(ProductOrderPage.ORDER_REGULAR_PRICE)).getText())
                .isEqualTo(mainPageRegularPrice);
        assertThat(findElement(By.xpath(ProductOrderPage.ORDER_DISCOUNT_PRICE)).getText())
                .isEqualTo(mainPageDiscountPrice);
        // (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
        color = findElement(By.xpath(ProductOrderPage.ORDER_REGULAR_PRICE)).getCssValue("color");
        assertThat(getHexColor(color)).isEqualTo(grayHex);
        color = findElement(By.xpath(ProductOrderPage.ORDER_DISCOUNT_PRICE)).getCssValue("color");
        assertThat(getHexColor(color)).isEqualTo(redHex);
    }

    private String getHexColor(String color) {
        return Color.fromString(color).asHex();
    }

}
