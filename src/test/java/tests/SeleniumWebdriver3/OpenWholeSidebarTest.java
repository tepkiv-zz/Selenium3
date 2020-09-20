package tests.SeleniumWebdriver3;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.litecart.AdminPage.SideBar;
import static com.litecart.AdminPage.breadcrumb;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenWholeSidebarTest extends BaseTest {
    String name;

    /**
     * 1. Open admin page
     * 2. Login to the admin page
     * 3. Click menu line
     * 4. Check menu line is reflected at the breadcrumb text
     * 5. Check menu sub line
     * 6. Check menu sub line is reflected at the breadcrumb text
     * 7. repeat 4-6 through all menu items
     */
    @Test
    public void openPageTest() {
        openAdmin();
        assertThat(driver.findElement(By.xpath(SideBar.menu)).isDisplayed()).isEqualTo(true);

        // Go through menu list
        for (int i = 0; driver.findElements(By.xpath(SideBar.menuLines)).size() > i; i++) {
            WebElement row = driver.findElements(By.xpath(SideBar.menuLines)).get(i);
            String title = row.findElement(By.xpath(SideBar.lineName)).getAttribute("title");
            System.out.println(title);
            row.click();
            // Check main page is showing header according to menu line text
            assertThat(driver.findElement(By.xpath(breadcrumb)).getText())
                    .as("Title should be displayed at the main page.").contains(title);

            // Go through menu item sub list
            for (int j = 0; j < driver.findElements(By.xpath(SideBar.menuLines)).get(i).findElements(By.xpath(SideBar.menuSubLine)).size(); j++) {
                WebElement subRow = driver.findElements(By.xpath(SideBar.menuLines)).get(i).findElements(By.xpath(SideBar.menuSubLine)).get(j);
                name = subRow.findElement(By.xpath(SideBar.lineName)).getText();
                System.out.println("\t*" + name);
                subRow.click();
                assertThat(driver.findElement(By.xpath(breadcrumb)).getText())
                        .as("Title should be displayed at the main page.").contains(title);
            }
        }
    }
}
