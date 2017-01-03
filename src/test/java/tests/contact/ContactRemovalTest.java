package tests.contact;

import fw.pages.ContactData;
import fw.pages.ContactHelper;
import tests.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactRemovalTest extends TestBase {

    @Test
    public void testContactRemoval() {
        ContactHelper contactHelper = app.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());
        // actions
        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.deleteContact(index);

        app.navigateTo().groupsPage();
        app.driver.navigate().refresh();
        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());

        //Collections.sort(oldList);
        assertThat(newList, equalTo(oldList.without(index)));
    }

}
