package tests.contact;

import fw.pages.ContactData;
import fw.pages.ContactHelper;
import fw.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.Random;
import static org.testng.Assert.assertEquals;

public class ContactRemovalTest extends TestBase {

    @Test
    public void testContactRemoval(){
        ContactHelper contactHelper = app.getContactHelper();
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        contactHelper.deleteContact(index);

        app.navigateTo().mainPage();
        app.driver.navigate().refresh();
        contactHelper.waitUntilPageLoads();

        ModifiedSortedList <ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());

        oldList.remove(index);
        System.out.println("old list " + oldList.size());

        Collections.sort(oldList);
        assertEquals(newList, oldList);//Actual Expected
    }

}
