package fw;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.*;



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
			ContactData contact = new ContactData();
			contact.firstName = generateRandomString();
			contact.lastName = "";
			contact.address1 = "";
			contact.home = "";
			contact.mobilephonenumber = "";
			contact.workphonenumber = "";
			contact.email1 = "";
			contact.email2 = "";
			contact.byear = "";
			contact.secondaryaddress = "";
			contact.secondaryhomephonenumber = "";
			list.add(new Object[]{contact});
		}
		return list.iterator();
	}

}
