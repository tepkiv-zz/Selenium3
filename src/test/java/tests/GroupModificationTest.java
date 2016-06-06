package tests;

import fw.GroupData;
import fw.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator(){
        List<Object[]> list = new ArrayList<Object[]>();
        //..
        for (int i = 0; i < 5;i++){
            GroupData group = new GroupData().withName(generateRandomString())
                    .withHeader(generateRandomString()).withFooter(generateRandomString());
            list.add(new Object[]{group});
        }
        return list.iterator();
    }


    @Test(dataProvider = "randomValidGroupGenerator")
    public void deleteGroup(GroupData group) {
        List<GroupData> oldList = app.getGroupHelper().getGroups();

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        app.getGroupHelper().modifyGroup(index,group);

        List <GroupData> newList = app.getGroupHelper().getGroups();

        // compare items in the lists
        oldList.remove(index);
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}

