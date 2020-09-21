package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.CreateUserPage;
import com.litecart.CreateUserPageHelper;
import com.litecart.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {
    String password = "password";

    @Test
    public void openPageTest() {
        CreateUserPageHelper createUserPageHelper = new CreateUserPageHelper(driver);

        driver.get("http://localhost/litecart/create_account");

        String email = generateEmail();
        // 1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
        createUserPageHelper.getInputField("First Name").sendKeys(RandomStringUtils.randomAlphanumeric(5));
        createUserPageHelper.getInputField("Last Name").sendKeys(RandomStringUtils.randomAlphanumeric(5));
        //getSelectField("Country").selectByIndex(1);
        createUserPageHelper.getSelectField("Zone/State/Province").selectByIndex(3);
        createUserPageHelper.getInputField("Email").sendKeys(email);
        createUserPageHelper.getInputField("Desired Password").sendKeys(password);
        createUserPageHelper.getInputField("Confirm Password").sendKeys(password);

        findElement(By.xpath(CreateUserPage.INPUT_I_VE_READ_PRIVACY_POLICY)).click();
        findElement(By.xpath(CreateUserPage.BUTTON_CREATE_ACCOUNT)).click();
        // 2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
        findElement(By.xpath(MainPage.SIGNIN_NAVIGATION_DROPDOWN)).click();
        findElement(By.xpath(MainPage.UserDropDown.LOGOUT)).click();

        // 3) повторный вход в только что созданную учётную запись,
        findElement(By.xpath(MainPage.SIGNIN_NAVIGATION_DROPDOWN)).click();
        findElement(By.xpath(MainPage.SignInDropdown.INPUT_EMAIL)).sendKeys(email);
        findElement(By.xpath(MainPage.SignInDropdown.INPUT_PASSWORD)).sendKeys(password);
        findElement(By.xpath(MainPage.SignInDropdown.BUTTON_SIGN_IN)).click();
        // 4) и ещё раз выход.
        findElement(By.xpath(MainPage.SIGNIN_NAVIGATION_DROPDOWN)).click();
        findElement(By.xpath(MainPage.UserDropDown.LOGOUT)).click();
    }

    public String generateEmail() {
        RandomStringUtils.randomAlphanumeric(17);
        StringBuilder sb = new StringBuilder();
        sb.append(RandomStringUtils.randomAlphanumeric(5))
                .append("@")
                .append(RandomStringUtils.randomAlphabetic(5))
                .append(".")
                .append(RandomStringUtils.randomAlphabetic(3)
                );

        return sb.toString();
    }

}
