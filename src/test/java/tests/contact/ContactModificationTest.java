package tests.contact;

import fw.pages.ContactData;
import fw.pages.ContactHelper;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class ContactModificationTest extends TestBase {


    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactModification(ContactData contact) {
        ContactHelper contactHelper = app.getContactHelper();

        List<ContactData> oldList = contactHelper.getContacts();
        System.out.println(oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        contactHelper.modifyContact(index,contact);


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

