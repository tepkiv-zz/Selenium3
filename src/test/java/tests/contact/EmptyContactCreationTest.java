package tests.contact;


import fw.ContactData;
import fw.ContactHelper;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class EmptyContactCreationTest extends TestBase {
    @Test
    public void TestEmptyContactCreation() throws Exception {
        app.navigateTo().mainPage();
        ContactHelper contactHelper = app.getContactHelper();
        List<ContactData> oldList = contactHelper.getContacts();

        contactHelper.openContactPage();
        ContactData contact = new ContactData("", "", "", "", "", "", "", "", "", "", "");

        contactHelper.fillContactForm(contact, true);
        contactHelper.submitContactCreation();
        contactHelper.openMainPage();
        contactHelper.waitUntilPageLoads();

        List<ContactData> newList = contactHelper.getContacts();
        assertEquals(newList, oldList);
    }
}
