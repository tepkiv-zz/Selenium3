package tests.group;

import com.TestBase;
import com.data.GroupData;
import com.helpers.product.GroupHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static com.data.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.data.GroupDataGenerator.loadGroupsFromXMLFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testValidGroupCreation(GroupData group) throws Exception {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getUiGroups();
       
        groupHelper.createGroup(group);
       
        ModifiedSortedList<GroupData> newList = groupHelper.getUiGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }

    @DataProvider
    public Iterator<Object[]> groupsFromXMLFile() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromXMLFile(new File("groups.xml"))).iterator();
    }

    @DataProvider
    public Iterator<Object[]> groupsFromCSVFile() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromCsvFile("groups.txt")).iterator();
    }

    @Test(dataProvider = "groupsFromXMLFile")
    public void testValidGroupCreationFromXMLFile(GroupData group) throws Exception {
        GroupHelper groupHelper = app.getGroupHelper();
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
        GroupHelper groupHelper = app.getGroupHelper();
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
        GroupHelper groupHelper = app.getGroupHelper();

        ModifiedSortedList<GroupData> oldList = app.getAppModel().getGroups();

        groupHelper.createGroup(group);

        ModifiedSortedList<GroupData> newList = app.getAppModel().getGroups();

        assertThat(newList, equalTo(oldList.withAdded(group)));
        if(ttc()){
            assertThat(app.getAppModel().getGroups(),equalTo(app.getGroupHelper().getUiGroups()));
        }

        if (app.getProperty("database.check").equals("true")){
            assertThat(app.getAppModel().getGroups(), equalTo(app.getHibernateHelper().getListOfGroups()));
        }
    }

}
