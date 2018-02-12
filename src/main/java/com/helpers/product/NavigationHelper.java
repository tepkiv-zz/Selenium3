package com.helpers.product;

import com.ApplicationManager;
import com.helpers.WebDriverHelperBase;
import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void groupsPage() {
        if (!onGroupsPage()) {
            click(By.linkText("groups"));
        }
    }

    private boolean onGroupsPage() {
        if (driver.getCurrentUrl().contains("group.php")
                && driver.findElements(By.name("new")).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onMainPage() {
        return driver.findElements(By.name("maintable")).size() > 0;
    }

    public void mainPage() {
        if (!onMainPage()) {
            click(By.linkText("home"));
        }
    }
}
