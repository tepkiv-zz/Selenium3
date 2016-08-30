package tests.group;

import fw.pages.GroupData;
import fw.pages.GroupHelper;
import fw.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import java.util.*;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class GroupModificationTest extends TestBase {


    @Test(dataProvider = "randomValidGroupGenerator")
    public void deleteGroup(GroupData group) {
        GroupHelper groupHelper = app.getGroupHelper();

        ModifiedSortedList<GroupData> oldList = groupHelper.getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        groupHelper.modifyGroup(index,group);

        ModifiedSortedList<GroupData> newList = groupHelper.getGroups();

        // compare items in the lists
        oldList.remove(index);
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}

