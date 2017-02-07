package tests.contact;

import com.data.ContactData;
import com.pages.ContactHelper;
import com.TestBase;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

public class EmptyContactCreationTest extends TestBase {

    @Test
    public void TestEmptyContactCreation() throws Exception {
        ContactHelper contactHelper = app.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        // actions
        contactHelper.openContactPage();
        ContactData contact = new ContactData("", "", "", "", "", "", "", "", "", "", "");

        contactHelper.fillContactForm(contact, true).submitContactUpdateOrCreation();
        contactHelper.openMainPage();

        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList));
    }
}
