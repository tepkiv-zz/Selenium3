package tests.SeleniumWebdriver3;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
            String count = findElement(By.xpath("//div[contains(@class,'badge quantity')]")).getText();
            // 3) подождать, пока счётчик товаров в корзине обновится
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(String.format("//div[contains(@class,'badge quantity')][contains(text(),'%s')]", count))));
            // 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
        }
        // 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        findElement(By.xpath("//div[@id='cart']")).click();
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@class='btn btn-danger']")));

        // 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
        findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//table[contains(@class,'items table')]//tr[@class='item']")));
    }
}
