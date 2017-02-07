package tests.group;

import com.data.GroupData;
import com.pages.GroupHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import com.TestBase;

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
}
