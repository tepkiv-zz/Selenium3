package tests.group;

import com.TestBase;
import com.data.GroupData;
import com.helpers.GroupHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static com.data.GroupDataGenerator.loadGroupsFromCsvFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testValidGroupCreation(GroupData group) throws Exception {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getGroups();
        // actions
        groupHelper.createGroup(group);
        // save new state
        ModifiedSortedList<GroupData> newList = groupHelper.getGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }

    @DataProvider
    public Iterator<Object[]> groupsFromFile() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromCsvFile("groups.txt")).iterator();
    }

    @Test(dataProvider = "groupsFromFile")
    public void testValidGroupCreationFromFile(GroupData group) throws Exception {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getGroups();
        // actions
        groupHelper.createGroup(group);
        // save new state
        ModifiedSortedList<GroupData> newList = groupHelper.getGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(group)));
    }
}
