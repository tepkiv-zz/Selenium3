package tests.group;

import fw.pages.GroupData;
import fw.pages.GroupHelper;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class GroupRemovalTest extends TestBase {

    GroupHelper groupHelper = app.getGroupHelper();

    @Test
    public void deleteGroup(){

        List<GroupData> oldList = groupHelper.getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        groupHelper.deleteGroup(index);

        List <GroupData> newList = groupHelper.getGroups();
        // compare items in the lists
        oldList.remove(index);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}
