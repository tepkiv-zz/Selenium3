package tests.group;

import com.BaseTest;
import com.data.GroupData;
import com.helpers.product.GroupHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupCreationTest extends BaseTest {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testValidGroupCreation(GroupData group) throws Exception {
        GroupHelper groupHelper = browserController.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getUiGroups();

        groupHelper.createGroup(group);

        ModifiedSortedList<GroupData> newList = groupHelper.getUiGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }

    @Test(dataProvider = "groupsFromXMLFile")
    public void testValidGroupCreationFromXMLFile(GroupData group) throws Exception {
        GroupHelper groupHelper = browserController.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getUiGroups();
       
        groupHelper.createGroup(group);
       
        ModifiedSortedList<GroupData> newList = groupHelper.getUiGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }

    @Test(dataProvider = "groupsFromCSVFile")
    public void testValidGroupCreationFromCSVFile(GroupData group) throws Exception {
        GroupHelper groupHelper = browserController.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getUiGroups();
       
        groupHelper.createGroup(group);
       
        ModifiedSortedList<GroupData> newList = groupHelper.getUiGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }

    @Test(dataProvider = "groupsFromXMLFile")
    public void testGroupCreationChecksFromDB(GroupData group) throws Exception {
        GroupHelper groupHelper = browserController.getGroupHelper();

        ModifiedSortedList<GroupData> oldList = browserController.getAppModel().getGroups();

        groupHelper.createGroup(group);

        ModifiedSortedList<GroupData> newList = browserController.getAppModel().getGroups();

        assertThat(newList, equalTo(oldList.withAdded(group)));
        if (ttc()) {
            assertThat(browserController.getAppModel().getGroups(), equalTo(browserController.getGroupHelper().getUiGroups()));
        }

        if (browserController.getProperty("database.check").equals("true")) {
            assertThat(browserController.getAppModel().getGroups(), equalTo(browserController.getHibernateHelper().getListOfGroups()));
        }
    }

}
