package fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ContactHelper extends HelperBase {

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public List<ContactData> getContacts(){

		List<ContactData> contacts = new ArrayList<ContactData>();

		//Find all checkboxes

		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));

		for (WebElement checkbox : checkboxes) {
			ContactData contact = new ContactData();
			String title = checkbox.getAttribute("title");
			contact.firstName = title.substring("Select (".length(),title.length() - ")".length());
			// <list> add()
			contacts.add(contact);
		}
		return contacts;
	}

	public ContactHelper(ApplicationManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}
	public void openMainPage() {
		// open main page
		driver.get(manager.baseUrl + "/addressbookv4.1.4/");
	}
	public void submitContactCreation() {
		click(By.name("submit"));
	}

	public void selectFromDropDownList(String groupName) {
		select(By.name("bday"), "4");
		select(By.name("bmonth"), "April");

		Select sel = new Select(driver.findElement(By.name("new_group")));
		try{
			sel.selectByValue(groupName);
		}catch (Exception e){
			sel.selectByIndex(1);
		}
	}

	public void fillContactForm(ContactData contactData, boolean formType) {
		
		type(By.name("firstname"),contactData.firstName);
		type(By.name("lastname"),contactData.lastName);
		type(By.name("address"),contactData.address1);
		type(By.name("home"),contactData.home);
		type(By.name("mobile"),contactData.mobilephonenumber);
		type(By.name("work"),contactData.workphonenumber);
		type(By.name("email"),contactData.email1);
		type(By.name("email2"),contactData.email2);
		type(By.name("byear"), contactData.byear);
		type(By.name("address2"), contactData.secondaryaddress);
		type(By.name("phone2"), contactData.secondaryhomephonenumber);
		 if(formType == CREATION){
			 selectFromDropDownList("group 1");
		}
		else{
			if(driver.findElements(By.name("new_group")).size() != 0){
				throw new Error("Group selector exists in contact modification form");
			}
		}
	}

	public void openContactPage() {
		click(By.linkText("add new"));
				//driver.findElement(By.linkText("add new")).click();
	}

	public void openContactDetails(int index){
		click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
		// click(By.xpath("//input[@name='selected[]'][" + index + "]"));

	}
	public void waitUntilPageLoads(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public void waitUntilContactListAppear(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("maintable")));
	}
	public void submitDeleteContact() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
	}
	public void refreshPage(){driver.navigate().refresh();}
}
