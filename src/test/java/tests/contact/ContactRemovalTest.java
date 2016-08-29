package tests.contact;

import fw.ContactData;
import fw.ContactHelper;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class ContactRemovalTest extends TestBase {

    @Test
    public void testContactRemoval(){
        app.navigateTo().mainPage();
        ContactHelper contactHelper = app.getContactHelper();
        List<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        contactHelper.openContactDetails(index).submitDeleteContact();

        app.navigateTo().mainPage();
        app.driver.navigate().refresh();
        contactHelper.waitUntilPageLoads();

        List <ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());

        oldList.remove(index);
        System.out.println("old list " + oldList.size());

        Collections.sort(oldList);
        assertEquals(newList, oldList);//Actual Expected
    }
}
