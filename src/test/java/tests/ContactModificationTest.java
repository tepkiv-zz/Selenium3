package tests;

import fw.ContactData;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by vpetrenko on 13.01.2015.
 */
public class ContactModificationTest extends TestBase {
    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactModification(ContactData contact) {
        app.navigateTo().mainPage();

        List<ContactData> oldList = app.getContactHelper().getContacts();
        System.out.println(oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size() - 1);

        app.getContactHelper().openContactDetails(index);
        app.getContactHelper().fillContactForm(contact,true);
        app.getGroupHelper().submitGroupModification();
        app.navigateTo().mainPage();
        app.getContactHelper().waitUntilPageLoads();
        List <ContactData> newList = app.getContactHelper().getContacts();
        System.out.println(newList.size());

        oldList.remove(index);
        oldList.add(contact);

        Collections.sort(oldList);
        Collections.sort(newList);
        assertEquals(newList, oldList);
    }
}

