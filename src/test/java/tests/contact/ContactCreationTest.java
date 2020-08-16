package tests.contact;

import com.BaseTest;
import com.data.ContactData;
import com.helpers.product.ContactHelper;
import com.utils.ModifiedSortedList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static com.data.ContactDataGenerator.loadContactsFromCsvFile;
import static com.data.ContactDataGenerator.loadGroupsFromXMLFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactCreationTest extends BaseTest {

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactCreation(ContactData contact) throws Exception {
        String generatedFirstname = generateRandomString();
        ContactHelper contactHelper = app.getContactHelper();

        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();
        System.out.println("old list " + oldList.size());

        // actions
        contactHelper.createContact(contact.withFirstName(generatedFirstname));

        //save new state
        contactHelper.waitUntilContactListAppear();
        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
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
    public Iterator<Object[]> contactsFromXMLFile() throws IOException {
        return wrapContactsForDataProvider(loadGroupsFromXMLFile(new File("contacts.xml"))).iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactsFromCSVFile() throws IOException {
        return wrapContactsForDataProvider(loadContactsFromCsvFile("contacts.txt")).iterator();
    }

    @Test(dataProvider = "contactsFromXMLFile")
    public void testValidGroupCreationFromXMLFile(ContactData contact) throws Exception {
        ContactHelper contactHelper = app.getContactHelper();

        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();

        contactHelper.createContact(contact);

        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
        //assertEquals(newList.size(), oldList.size() + 1);
        //1Collections.sort(oldList);
        //assertEquals(newList, oldList);
        assertThat(newList, equalTo(oldList.withAdded(contact)));
    }

    @Test(dataProvider = "contactsFromCSVFile")
    public void testContactCreationFromFile(ContactData contact) throws Exception {
        String generatedFirstname = generateRandomString();
        ContactHelper contactHelper = app.getContactHelper();

        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getUiContacts();
        System.out.println("old list " + oldList.size());

        // actions
        contactHelper.createContact(contact.withFirstName(generatedFirstname));

        //save new state
        contactHelper.waitUntilContactListAppear();
        ModifiedSortedList<ContactData> newList = contactHelper.getUiContacts();
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

    @Test(dataProvider = "contactsFromXMLFile")
    public void testGroupCreationChecksFromDB(ContactData contact) throws Exception {
        ContactHelper contactHelper = app.getContactHelper();

        ModifiedSortedList<ContactData> oldList =  app.getAppModel().getContacts();

        contactHelper.createContact(contact);

        ModifiedSortedList<ContactData> newList =  app.getAppModel().getContacts();

        assertThat(newList, equalTo(oldList.withAdded(contact)));


        assertThat(app.getAppModel().getContacts(),equalTo(app.getHibernateHelper().getListOfContacts()));

        assertThat(app.getAppModel().getContacts(),equalTo(app.getContactHelper().getUiContacts()));
    }

}
