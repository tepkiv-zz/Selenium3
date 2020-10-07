package tests.contact;

import com.BaseTest;
import com.data.ContactData;
import com.helpers.product.ContactHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EmptyContactCreationTest extends BaseTest {

    @Test
    public void TestEmptyContactCreation() throws Exception {
        ContactHelper contactHelper = browserController.getContactHelper();
        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();
        // actions
        contactHelper.openContactPage();
        ContactData contact = new ContactData("", "", "", "", "", "", "", "", "", "", "");

        contactHelper.fillContactForm(contact, true).submitContactUpdateOrCreation();
        contactHelper.openMainPage();

        contactHelper.waitUntilPageLoads();
        // save new state
        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList));
    }
}
