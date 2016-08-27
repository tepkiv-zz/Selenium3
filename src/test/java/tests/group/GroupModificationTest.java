package tests.group;

import fw.GroupData;
import fw.GroupHelper;
import fw.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {
    GroupHelper groupHelper = app.getGroupHelper();

    @Test(dataProvider = "randomValidGroupGenerator")
    public void deleteGroup(GroupData group) {
        app.navigateTo().mainPage();

        List<GroupData> oldList = groupHelper.getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        groupHelper.modifyGroup(index,group);

        List <GroupData> newList = groupHelper.getGroups();

        // compare items in the lists
        oldList.remove(index);
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}

