package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.AdminPage;
import com.litecart.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class test extends BaseTest {
    private String loginpassword = "admin";

    @Test
    public void openPageTest() {
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(LoginPage.USERNAME).sendKeys(loginpassword);
        driver.findElement(LoginPage.USERNAME).sendKeys(loginpassword);

        driver.findElement(LoginPage.LOGIN).click();

        assertThat(driver.findElement(AdminPage.LEFT_MENU_BOX).isDisplayed())
                .isEqualTo("Left menu box is not present after login").isEqualTo(true);
    }

}
