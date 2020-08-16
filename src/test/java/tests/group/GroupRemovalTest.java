package tests.group;

import com.BaseTest;
import com.data.GroupData;
import com.helpers.product.GroupHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class GroupRemovalTest extends BaseTest {

    @Test
    public void deleteGroup() {
        GroupHelper groupHelper = app.getGroupHelper();
        // save old state
        ModifiedSortedList<GroupData> oldList = groupHelper.getUiGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);
        // actions
        groupHelper.deleteGroup(index);
        // save new state
        ModifiedSortedList<GroupData> newList = groupHelper.getUiGroups();
        // compare items in the lists
        //Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.without(index)));
    }
}
