package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.litecart.SidebarMenuEnum;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.litecart.AdminPage.SideBar;

public class AdminPageHelper extends BaseTest {

    public void clickMenuItem(SidebarMenuEnum menuEnum) {
        findElement(By.xpath(String.format(SideBar.specificMenuLine, menuEnum.getName()))).click();
    }

    public void clickSubmenu(SidebarMenuEnum menuEnum) {
        String[] s = menuEnum.name().split("_");
        String subCatName = Arrays.stream(s).findFirst().get();

        String menuLine = String.format(SideBar.specificMenuLine, subCatName);
        findElement(By.xpath(menuLine)).click();
        String menuSubline = String.format(SideBar.specificMenuSubLine, subCatName, menuEnum.getName());
        findElement(By.xpath(menuSubline)).click();
    }

}
