package tests.contact;

import fw.pages.ContactData;
import fw.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import fw.pages.ContactHelper;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void TestContactCreation(ContactData contact) throws Exception {
        String generatedFirstname = generateRandomString();
        ContactHelper contactHelper = app.getContactHelper();

        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());

        // actions
        contactHelper.createContact(contact.withFirstName(generatedFirstname));

        //save new state
        contactHelper.waitUntilContactListAppear();
        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());
        // compare states
        //assertEquals(newList.size(), (oldList.size() + 1));

        // compare items in the lists
        //oldList.add(contact.withFirstName(generatedFirstname));

        System.out.println("old list " + oldList.size());
        //assertEquals(newList, oldList,"Should be equals");//Actual Expected

        assertThat(newList,equalTo(oldList.withAdded(contact.withFirstName(generatedFirstname))));
    }

}
