package tests.group;

import com.data.GroupData;
import com.pages.GroupHelper;
import com.TestBase;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class GroupModificationTest extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void deleteGroup(GroupData group) {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getGroups();
        System.out.println("old list " + oldList.size());
        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        groupHelper.modifyGroup(index, group);
        // save new state
        ModifiedSortedList<GroupData> newList = groupHelper.getGroups();
        System.out.println("newList list " + newList.size());
        // compare items in the lists
        oldList.remove(index);
        oldList.add(group);
        //Collections.sort(oldList);
        //assertEquals(newList,oldList);
        assertThat(newList, equalTo(oldList.without(index)));
    }
}

