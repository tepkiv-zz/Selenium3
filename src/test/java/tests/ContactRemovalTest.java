package tests;

import fw.ContactData;
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
        List<ContactData> oldList = app.getContactHelper().getContacts();
        System.out.println("old list " + oldList.size());

        Random rnd = new Random();
        int index = rnd.nextInt(oldList.size()-1);

        app.getContactHelper().openContactDetails(index);
        app.getContactHelper().submitDeleteContact();
        app.navigateTo().mainPage();

        app.driver.navigate().refresh();
        app.getContactHelper().waitUntilPageLoads();

        List <ContactData> newList = app.getContactHelper().getContacts();
        System.out.println("newList list " + newList.size());

        oldList.remove(index);
        System.out.println("old list " + oldList.size());

        Collections.sort(oldList);
        assertEquals(newList, oldList);//Actual Expected
    }
}
