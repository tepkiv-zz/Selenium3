package fw.pages;

import fw.ApplicationManager;
import fw.HelperBase;
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

    public void deleteContact(int index) {
        openContactDetails(index).submitDeleteContact();
    }

    public ContactHelper modifyContact(int index, ContactData contact) {
        openContactDetails(index).fillContactForm(contact,ContactHelper.CREATION).submitContactUpdateOrCreation();
        openMainPage();
        return this;
    }
    public ContactHelper createContact(ContactData contact) {
        openContactPage().fillContactForm(contact, CREATION).submitContactUpdateOrCreation();
        openMainPage();
        return this;
    }

    private List<ContactData> cachedContacts  = new ArrayList<ContactData>();

    public List<ContactData> getContacts() {
        if (cachedContacts == null) {
            rebuildCache();
        }
        return cachedContacts;
    }

    public void rebuildCache() {
        manager.navigateTo().mainPage();
        //List<ContactData> cachedContacts = new ArrayList<ContactData>();
        //Find all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            String title = checkbox.getAttribute("title");
            // <list> add()
            cachedContacts.add(new ContactData().withFirstName(title.substring("Select (".length(), title.length() - ")".length()).trim()));
        }
    }

    public ContactHelper(ApplicationManager manager) {
        super(manager);
        // TODO Auto-generated constructor stub
    }

    private boolean onСontactsPage(){
        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("/edit.php") &&
                driver.findElement(By.xpath("//input[@value='Enter' and @type='submit' and @name='submit']")).isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public ContactHelper openMainPage() {
        // open main page
        driver.get(manager.baseUrl + "/addressbookv4.1.4/");
        return this;
    }

    public ContactHelper submitContactUpdateOrCreation() {
        String customXpath = "//input[@name='submit' or @name='update']";
        driver.findElement(By.xpath(customXpath)).click();
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
        List<WebElement> editLinks = driver.findElements(By.xpath("//a[contains(@href,'edit.php')]"));
        editLinks.get(index).click();
        // click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
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




}
