package tests.contact;

import com.TestBase;
import com.data.ContactData;
import com.helpers.ContactHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static com.data.ContactDataGenerator.loadContactsFromCsvFile;
import static com.data.GroupDataGenerator.loadGroupsFromCsvFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
        //oldList.add(contact.withFirstName(generatedFirstname));
        //assertTrue(newList.equals(oldList));
        assertThat(newList, equalTo(oldList.withAdded(contact.withFirstName(generatedFirstname))));
    }


    @DataProvider
    public Iterator<Object[]> groupsFromFile() {
        return wrapGroupsForDataProvider(loadContactsFromCsvFile("contacts.txt")).iterator();
    }

    @Test(dataProvider = "groupsFromFile")
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
        //oldList.add(contact.withFirstName(generatedFirstname));
        //assertTrue(newList.equals(oldList));
        assertThat(newList, equalTo(oldList.withAdded(contact.withFirstName(generatedFirstname))));
    }

}
