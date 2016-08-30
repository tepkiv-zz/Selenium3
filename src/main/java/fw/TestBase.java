package fw;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fw.pages.ContactData;
import fw.pages.GroupData;
import org.testng.annotations.*;



public class TestBase {
	protected ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
		}

	@AfterTest
	public void tearDown() throws Exception {
		//app.stop();
	}



	public String generateRandomString(){
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0) {
			return "";
		}
		else {
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
    public Iterator<Object[]> randomValidGroupGenerator(){
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < 5;i++){
            GroupData group = new GroupData().withName(generateRandomString())
                    .withHeader(generateRandomString()).withFooter(generateRandomString());
            list.add(new Object[]{group});
        }
        return list.iterator();
    }

}
