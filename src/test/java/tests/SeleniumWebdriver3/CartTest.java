package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.CheckoutPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void addToCart() {
        // 1) открыть главную страницу
        driver.get("http://localhost/litecart");
        // 2) открыть первый товар из списка
        findElement(By.xpath("//*[@id='box-campaign-products']//a")).click();
        for (int i = 1; i <= 3; i++) {
            // 2) добавить его в корзину (при этом может случайно добавиться товар, кото рый там уже есть, ничего страшного)
            findElement(By.xpath("//*[@name='add_cart_product']")).click();
            String count = findElement(By.xpath(CheckoutPage.checkoutBag)).getText();
            // 3) подождать, пока счётчик товаров в корзине обновится
            waitElementPresence(By.xpath(String.format(CheckoutPage.checkoutBag + "[contains(text(),'%s')]", count)));
            // 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
        }
        // 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        findElement(CheckoutPage.cart).click();
        waitElementPresence(By.xpath("//button[@class='btn btn-danger']"));

        // 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
        findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        waitElementInvisibility(CheckoutPage.checkoutTable);
    }

}
