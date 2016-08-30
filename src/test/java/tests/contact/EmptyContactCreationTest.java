package tests.contact;

import fw.pages.ContactData;
import fw.pages.ContactHelper;
import fw.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EmptyContactCreationTest extends TestBase {
    @Test
    public void TestEmptyContactCreation() throws Exception {
        ContactHelper contactHelper = app.getContactHelper();
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();

        contactHelper.openContactPage();
        ContactData contact = new ContactData("", "", "", "", "", "", "", "", "", "", "");

        contactHelper.fillContactForm(contact, true).submitContactUpdateOrCreation();
        contactHelper.openMainPage();

        contactHelper.waitUntilPageLoads();

        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        assertEquals(newList, oldList);
    }
}
