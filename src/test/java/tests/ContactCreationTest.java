    package tests;

import fw.ContactData;
import fw.TestBase;
import org.testng.annotations.Test;
import fw.ContactHelper;

import java.util.*;

import static org.testng.Assert.assertEquals;


public class ContactCreationTest extends TestBase {



    @Test(dataProvider = "randomValidContactGenerator")
     public void TestContactCreation(ContactData contact) throws Exception {
	 app.navigateTo().mainPage();

     // save old state
     List<ContactData> oldList = app.getContactHelper().getContacts();
     System.out.println("old list " + oldList.size());

     // actions
     app.getContactHelper().openContactPage();
	 app.getContactHelper().fillContactForm(contact, ContactHelper.CREATION);
     app.getContactHelper().submitContactCreation();
     app.getContactHelper().openMainPage();

     //save new state
     app.getContactHelper().refreshPage();
     app.getContactHelper().waitUntilContactListAppear();
     List <ContactData> newList = app.getContactHelper().getContacts();
     System.out.println("newList list " + newList.size());
     // compare states
     //assertEquals(newList.size(), (oldList.size() + 1));

     // compare items in the lists
     contact.firstName = contact.firstName + " ";
     oldList.add(contact);

     System.out.println("old list " + oldList.size());
     Collections.sort(oldList);
        Collections.sort(newList);
     assertEquals(newList, oldList);//Actual Expected
  }
}
