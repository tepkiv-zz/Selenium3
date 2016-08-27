package tests.contact;

import fw.ContactData;
import fw.ContactHelper;
import fw.GroupHelper;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class ContactModificationTest extends TestBase {
    GroupHelper groupHelper = app.getGroupHelper();

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactModification(ContactData contact) {
        app.navigateTo().mainPage();

        ContactHelper contactHelper = app.getContactHelper();
        List<ContactData> oldList = contactHelper.getContacts();
        System.out.println(oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.openContactDetails(index);
        contactHelper.fillContactForm(contact,true);
        groupHelper.submitGroupModification();

        app.navigateTo().mainPage();
        contactHelper.waitUntilPageLoads();
        List <ContactData> newList = contactHelper.getContacts();
        System.out.println(newList.size());

        oldList.remove(index);
        oldList.add(contact);

        Collections.sort(oldList);
        Collections.sort(newList);
        assertEquals(newList, oldList);
    }
}

