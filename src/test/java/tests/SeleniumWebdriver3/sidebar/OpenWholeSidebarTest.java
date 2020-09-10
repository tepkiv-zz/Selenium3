package tests.SeleniumWebdriver3.sidebar;

import com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.SeleniumWebdriver3.sidebar.AdminPage.breadcrumb;

public class OpenWholeSidebarTest extends BaseTest {
    String name;

    /**
     * 1. Open admin page
     * 2. Login to the admin page
     * 3. Click menu line
     * 4. Check menu line is reflected at the breadcrumb text
     * 5. Check menu sub line
     * 6. Check menu sub line is reflected at the breadcrumb text
     */
    @Test
    public void openPageTest() {
        openAdmin();
        assertThat(driver.findElement(By.xpath(AdminPage.SideBar.menu)).isDisplayed()).isEqualTo(true);

        // Go through menu list
        for (int i = 0; i < driver.findElements(AdminPage.SideBar.menuLines).size(); i++) {
            WebElement row = driver.findElements(AdminPage.SideBar.menuLines).get(i);
            String title = row.findElement(AdminPage.SideBar.lineName).getAttribute("title");
            System.out.println(title);
            row.click();
            // Check main page is showing header according to menu line text
            assertThat(driver.findElement(By.xpath(breadcrumb)).getText())
                    .as("Title should be displayed at the main page.").contains(title);

            // Go through menu item sub list
            for (int j = 0; j < driver.findElements(AdminPage.SideBar.menuLines).get(i).findElements(AdminPage.SideBar.menuSubLine).size(); j++) {
                WebElement subRow = driver.findElements(AdminPage.SideBar.menuLines).get(i).findElements(AdminPage.SideBar.menuSubLine).get(j);
                name = subRow.findElement(AdminPage.SideBar.lineName).getText();
                System.out.println("\t*" + name);
                subRow.click();
                assertThat(driver.findElement(By.xpath(breadcrumb)).getText())
                        .as("Title should be displayed at the main page.").contains(title);
            }
        }
    }
}
