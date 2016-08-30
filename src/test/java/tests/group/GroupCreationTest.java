package tests.group;

import static org.testng.Assert.assertEquals;
import fw.pages.GroupData;
import fw.pages.GroupHelper;
import fw.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import java.util.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

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
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}
