package fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	public void groupsPage() {
		if(!onGroupsPage()){
			click(By.linkText("groups"));
		}

	}

	private boolean onGroupsPage() {
		if(driver.getCurrentUrl().contains("group.php")
				&& driver.findElements(By.name("new")).size()>0){
			return true;
		}
		else{
			return false;
		}
	}
    private boolean onMainPage() {
        return driver.findElements(By.name("maintable")).size()>0;
    }

	public void mainPage() {
        if (!onMainPage()) {
            driver.get(manager.baseUrl + "/addressbookv4.1.4/");
        }
    }
}
