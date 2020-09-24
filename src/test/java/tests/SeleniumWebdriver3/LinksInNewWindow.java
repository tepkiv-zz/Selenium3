package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.AdminPageHelper;
import com.litecart.SidebarMenuEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.litecart.CountriesPage.CONTENT_TABLE_ROWS;
import static com.litecart.CountriesPage.COUNTRY_COLUMN;

public class LinksInNewWindow extends BaseTest {
    @Test
    public void addToCart() {
        //  зайти в админку
        openAdmin();
        //2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
        new AdminPageHelper().clickMenuItem(SidebarMenuEnum.Countries);
        // 3) открыть на редактирование какую-нибудь страну или начать создание новой
        // Go through countries
        List<String> codes = new ArrayList<>();
        By editCountry = By.xpath(CONTENT_TABLE_ROWS + COUNTRY_COLUMN.replace(".", "") + String.format("[contains(text(),'%s')]", "Afghanistan"));
        findElement(editCountry).click();
        //4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой
        // -- они ведут на внешние страницы и открываются в новом окне,
        // именно это и нужно проверить.
        List<WebElement> externalLinks = driver.findElements(By.xpath("//*[@class='fa fa-external-link']"));
        String defaultTab = driver.getWindowHandle();

        // Конечно, можно просто убедиться в том, что у ссылки есть атрибут target = "_blank".
        // Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
        // потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти
        // действия для всех таких ссылок.

        for (WebElement element : externalLinks) {
            element.click();
            ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(allTabs.get(1));
            driver.close();
            // change focus back to old tab
            driver.switchTo().window(defaultTab);
        }


//                Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.

    }
}
