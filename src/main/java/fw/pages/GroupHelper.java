package fw.pages;

import fw.ApplicationManager;
import fw.HelperBase;
import fw.utils.ModifiedList;
import fw.utils.ModifiedSortedList;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper createGroup(GroupData group){
		manager.navigateTo().groupsPage();
		initGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		rebuildCache();
		return this;
	}

	public GroupHelper deleteGroup(int index) {
		selectGroupByIndex(index);
		submitGroupRemoval();
		returnToGroupsPage();
		rebuildCache();
		return this;
	}
	public GroupHelper modifyGroup(int index, GroupData group) {
		initGroupModification(index);
		// create group object
		group.setName("new name");
		group.setFooter("new footer");
		group.setHeader("new header");
		fillGroupForm(group);
		submitGroupModification();
		returnToGroupsPage();
		rebuildCache();
		return this;
	}

    private ModifiedSortedList<GroupData> cachedGroups;

	public ModifiedSortedList<GroupData> getGroups() {
        if (cachedGroups == null) {
            rebuildCache();
        }
        return cachedGroups;
    }

    private void rebuildCache() {
        cachedGroups = new ModifiedSortedList<GroupData>();

        manager.navigateTo().groupsPage();
        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            String title = checkbox.getAttribute("title");
            String name = title.substring("Select (".length(),title.length() - ")".length());
            cachedGroups.add(new GroupData().withName(name));
        }
	}

	/**
	 * -----------------------------------------------------------------------------------------------------------------
	 */
	private GroupHelper submitGroupRemoval() {
		click(By.name("delete"));
		cachedGroups = null;
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
        cachedGroups = null;
		return this;
	}

	public GroupHelper initGroupCreation() {
		manager.navigateTo().groupsPage();
		click(By.name("new"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		// fill group form
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;

	}
	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		cachedGroups = null;
		return this;
	}

	private boolean onGroupsPage(){
	    String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("/group.php") &&  driver.findElements(By.name("new")).size() > 0){
            return true;
        }else{
            return false;
        }
    }
}
