package tests.group;

import com.data.GroupData;
import com.pages.GroupHelper;
import com.TestBase;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class GroupRemovalTest extends TestBase {

    @Test
    public void deleteGroup() {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);
        // actions
        groupHelper.deleteGroup(index);
        // save new state
        ModifiedSortedList<GroupData> newList = groupHelper.getGroups();
        // compare items in the lists
        //Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.without(index)));
    }
}
