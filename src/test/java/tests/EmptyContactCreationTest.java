package tests;



import fw.ContactData;
import fw.TestBase;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class EmptyContactCreationTest extends TestBase {
  @Test
    public void TestEmptyContactCreation() throws Exception {
      app.navigateTo().mainPage();
      List<ContactData> oldList = app.getContactHelper().getContacts();

      app.getContactHelper().openContactPage();
      ContactData contact = new ContactData("", "", "", "", "", "", "", "", "", "", "");

      app.getContactHelper().fillContactForm(contact,true);
      app.getContactHelper().submitContactCreation();
      app.getContactHelper().openMainPage();
      app.getContactHelper().waitUntilPageLoads();

      List <ContactData> newList = app.getContactHelper().getContacts();
      assertEquals(newList, oldList);
  }
}
