package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class AddingNewProductTest extends BaseTest {

    @Test
    public void openPageTest() {
        openAdmin();
        // Для добавления товара нужно открыть меню Catalog
        new AdminPageHelper().clickMenuItem(SidebarMenuEnum.Catalog);
        // в правом верхнем углу нажать кнопку "Add New Product",
        findElement(By.xpath(AdminPage.Catalog.BUTTON_ADD_NEW_PRODUCT)).click();
        // Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
        // General
        AddNewProductPageHelper.openTab("General");
        AddNewProductPageHelper.getInput("Name").sendKeys("NEW SUPER");
        AddNewProductPageHelper.getInput("Code").sendKeys("Code");
        AddNewProductPageHelper.findSelect("Manufacturer").selectByIndex(0);
        LocalDateTime ldt = LocalDateTime.now();
        String format = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt);
        AddNewProductPageHelper.getInput("Date Valid From").sendKeys(format);
        AddNewProductPageHelper.getInput("Date Valid To").sendKeys(format);
        findElement(By.xpath(AddNewProductPage.IMAGE_INPUT)).sendKeys(new File(RESOURCES_FOLDER + "download.png").getAbsolutePath());
        // Information
        AddNewProductPageHelper.openTab("Information");
        AddNewProductPageHelper.getInput("Short Description").sendKeys(format);
        AddNewProductPageHelper.getInput("Date Valid To").sendKeys(format);

        findElement(By.xpath(AddNewProductPage.TEXTAREA_DESCRIPTION)).sendKeys("test");
        findElement(By.xpath(AddNewProductPage.TEXTAREA_TECHNICAL_DATA)).sendKeys("test");
        AddNewProductPageHelper.getInput("Head Title").sendKeys(format);
        AddNewProductPageHelper.getInput("Meta Description").sendKeys(format);

        // Prices
        AddNewProductPageHelper.openTab("Prices");
        AddNewProductPageHelper.findSelect("Manufacturer").selectByValue("USD");
        AddNewProductPageHelper.getInput("Purchase Price").sendKeys("1111");

        Select select = new Select(driver.findElement(By.xpath("//*[@name='purchase_price_currency_code']")));
        select.selectByVisibleText("Euros");

        findElement(By.xpath(AddNewProductPage.INPUT_PRICE_EURO)).sendKeys("11");
        findElement(By.xpath(AddNewProductPage.INPUT_PRICE_USD)).sendKeys("11");
        findElement(By.xpath(AddNewProductPage.BUTTON_SAVE)).click();

        // После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
        assertThat(findElement(By.xpath("//div[@class='panel-body']")).getText()).isEqualTo("NEW SUPER");

    }

}
