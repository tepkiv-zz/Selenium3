package tests;

import fw.GroupData;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by vpetrenko on 22.12.2014.
 */
public class GroupRemovalTest extends TestBase {
    @Test
    public void deleteGroup(){
        List<GroupData> oldList = app.getGroupHelper().getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        app.getGroupHelper().deleteGroup(index);

        List <GroupData> newList = app.getGroupHelper().getGroups();
        // compare items in the lists
        oldList.remove(index);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}
