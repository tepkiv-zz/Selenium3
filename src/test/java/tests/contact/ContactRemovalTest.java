package tests.contact;

import com.BaseTest;
import com.data.ContactData;
import com.helpers.product.ContactHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactRemovalTest extends BaseTest {

    @Test
    public void testContactRemoval() {
        ContactHelper contactHelper = app.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();
        System.out.println("old list " + oldList.size());
        // actions
        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.deleteContact(index);

        app.getNavigationHelper().groupsPage();
        app.getDriver().navigate().refresh();
        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
        System.out.println("newList list " + newList.size());

        //Collections.sort(oldList);
        assertThat(newList, equalTo(oldList.without(index)));
    }

}
