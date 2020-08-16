package tests.contact;

import com.BaseTest;
import com.data.ContactData;
import com.helpers.product.ContactHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactModificationTest extends BaseTest {


    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactModification(ContactData contact) {
        ContactHelper contactHelper = app.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();
        System.out.println(oldList.size());
        // actions
        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.modifyContact(index, contact);
        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
        System.out.println(newList.size());

        oldList.remove(index);
        oldList.add(contact);

        assertThat(newList, equalTo(oldList));
    }
}

