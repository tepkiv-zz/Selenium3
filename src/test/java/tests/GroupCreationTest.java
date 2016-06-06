package tests;

import static org.testng.Assert.assertEquals;

import fw.GroupData;
import fw.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator(){
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < 5;i++){
            GroupData group = new GroupData().withName(generateRandomString())
                    .withHeader(generateRandomString()).withFooter(generateRandomString());
            list.add(new Object[]{group});
        }
        return list.iterator();
    }

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testValidGroupCreation(GroupData group) throws Exception {

        List<GroupData> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().createGroup(group);

        List<GroupData> newList = app.getGroupHelper().getGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}
