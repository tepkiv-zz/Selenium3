package tests;


import fw.ApplicationManager;
import fw.pages.ContactData;
import fw.pages.GroupData;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static tests.group.GroupDataGenerator.generateRandomGroups;


public class TestBase {
    protected ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
        app = new ApplicationManager();
    }

    @AfterTest
    public void tearDown() throws Exception {
        app.stop();
    }

    public String generateRandomString() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 0) {
            return "";
        } else {
            return "test" + rnd.nextInt();
        }
    }

    @DataProvider
    public Iterator<Object[]> randomValidContactGenerator() {
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < 1; i++) {
            ContactData contact = new ContactData()
                    .withFirstName("").withLastName("").withAddress1("").withHome("").withMobilePhoneNumber("").withWorkPhoneNumber("")
                    .withEmail1("").withEmail2("").withBirthDayYear("").withSecondaryAddress("").withSecondaryPhoneNumber("");

            list.add(new Object[]{contact});
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        return wrapGroupsForDataProvider(generateRandomGroups(1)).iterator();
    }

    private List<Object[]> wrapGroupsForDataProvider(List<GroupData> listGroupData) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (GroupData group : listGroupData) {
            list.add(new Object[]{group});
        }
        return list;
    }

}
