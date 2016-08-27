package tests.group;

import fw.GroupData;
import fw.GroupHelper;
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
        app.navigateTo().mainPage();
        app.navigateTo().groupsPage();

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
