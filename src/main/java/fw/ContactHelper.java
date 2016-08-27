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

    public List<ContactData> getContacts() {

        List<ContactData> contacts = new ArrayList<ContactData>();

        //Find all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));

        for (WebElement checkbox : checkboxes) {
            String title = checkbox.getAttribute("title");
            // <list> add()
            contacts.add(new ContactData().withFirstName(title.substring("Select (".length(), title.length() - ")".length()).trim()));
        }
        return contacts;
    }

    public ContactHelper(ApplicationManager manager) {
        super(manager);
        // TODO Auto-generated constructor stub
    }

    public ContactHelper openMainPage() {
        // open main page
        driver.get(manager.baseUrl + "/addressbookv4.1.4/");
        return this;
    }

    public ContactHelper submitContactCreation() {
        click(By.name("submit"));
        return this;
    }

    public ContactHelper selectFromDropDownList(String groupName) {
        select(By.name("bday"), "4");
        select(By.name("bmonth"), "April");

        if (driver.findElements(By.name("new_group")).size() != 0) {
            Select sel = new Select(driver.findElement(By.name("new_group")));
            try {
                sel.selectByValue(groupName);
            } catch (Exception e) {
                sel.selectByIndex(1);
            }
        }
        return this;
    }

    public ContactHelper fillContactForm(ContactData contactData, boolean formType) {

        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress1());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobilephonenumber());
        type(By.name("work"), contactData.getWorkphonenumber());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("byear"), contactData.getByear());
        type(By.name("address2"), contactData.getSecondaryaddress());
        type(By.name("phone2"), contactData.getSecondaryhomephonenumber());
        if (formType == CREATION) {
            selectFromDropDownList("group 1");
        } else {
            if (driver.findElements(By.name("new_group")).size() != 0) {
                throw new Error("Group selector exists in contact modification form");
            }
        }
        return this;
    }

    public ContactHelper openContactPage() {
        click(By.linkText("add new"));
        //driver.findElement(By.linkText("add new")).click();
        return this;
    }

    public ContactHelper openContactDetails(int index) {
        waitUntilContactListAppear();
        click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
        // click(By.xpath("//input[@name='selected[]'][" + index + "]"));
        return this;

    }

    public void waitUntilPageLoads() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void waitUntilContactListAppear() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("maintable")));
    }

    public ContactHelper submitDeleteContact() {
        click(By.xpath("//input[@type='submit'][@value='Delete']"));
        return this;
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
