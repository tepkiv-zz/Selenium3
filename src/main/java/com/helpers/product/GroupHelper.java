package com.helpers.product;

import com.ApplicationManager;
import com.data.GroupData;
import com.data.GroupDataGenerator;
import com.helpers.WebDriverHelperBase;
import com.utils.ModifiedSortedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GroupHelper extends WebDriverHelperBase {

    private ModifiedSortedList<GroupData> cachedGroups;

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public GroupHelper createGroup(GroupData group) {
        manager.getNavigationHelper().groupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        manager.getAppModel().addGroup(group);
        rebuildCache();
        return this;
    }

    public GroupHelper deleteGroup(int index) {
        selectGroupByIndex(index);
        submitGroupRemoval();
        returnToGroupsPage();
        manager.getAppModel().removeGroup(index);
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
        manager.getAppModel().removeGroup(index).addGroup(group);
        rebuildCache();
        return this;
    }

    public ModifiedSortedList<GroupData> getUiGroups() {
        if (cachedGroups == null) {
            rebuildCache();
        }
        return cachedGroups;
    }

    private void rebuildCache() {
        cachedGroups = new ModifiedSortedList<GroupData>();

        manager.getNavigationHelper().groupsPage();
        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            String title = checkbox.getAttribute("title");
            String name = title.substring("Select (".length(), title.length() - ")".length());
            cachedGroups.add(new GroupData().withName(name));
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    private GroupHelper submitGroupRemoval() {
        click(By.name("delete"));
        clearCache();
        return this;
    }

    public GroupHelper submitGroupCreation() {
        click(By.name("submit"));
        clearCache();
        return this;
    }

    private void clearCache() {
       if(cachedGroups!=null){
            cachedGroups.clear();
        }
    }

    public GroupHelper initGroupCreation() {
        manager.getNavigationHelper().groupsPage();
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
        clearCache();
        return this;
    }

    private boolean onGroupsPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("/group.php") && driver.findElements(By.name("new")).size() > 0;
    }

    /* Group */
    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        return wrapGroupsForDataProvider(GroupDataGenerator.generateRandomGroups(3)).iterator();
    }

    protected List<Object[]> wrapGroupsForDataProvider(List<GroupData> listGroupData) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (GroupData group : listGroupData) {
            list.add(new Object[]{group});
        }
        return list;
    }

}
