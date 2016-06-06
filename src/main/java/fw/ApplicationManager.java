package fw;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ApplicationManager {

	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	
	public  String baseUrl;

	public  WebDriver driver;
	
	public ApplicationManager(){

      //  File chromeDriver = new File("D:\\git\\programming-for-testers\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath() );
		driver = new FirefoxDriver();
        //driver = new ChromeDriver();
		baseUrl = "http://127.0.0.1:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//navigationHelper = new NavigationHelper(this);
		//groupHelper = new GroupHelper(this);
		//contactHelper = new ContactHelper(this);
	}
	
	public void stop() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	
	public NavigationHelper navigateTo(){
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
	
	public GroupHelper getGroupHelper(){
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}
	
	public ContactHelper getContactHelper(){
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}


}
