package com;

import com.helpers.ContactHelper;
import com.helpers.GroupHelper;
import com.helpers.NavigationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
	public  String baseUrl;
	public  WebDriver driver;

	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
    private Properties properties;

	public ApplicationManager(Properties properties){
	    this.properties = properties;

	    String browser = properties.getProperty("browser");
	    if("firefox".equals(browser)){
	        driver = new FirefoxDriver();
            //System.setProperty("webdriver.firefox.marionette", "C:\\repo\\exercises\\test_excercises_new\\src\\test\\resources\\geckodriver.exe");
            //System.setProperty("webdriver.gecko.driver", "C:\\repo\\exercises\\test_excercises_new\\src\\test\\resources\\geckodriver.exe");
        }
        else if("googlechrome".equals(browser)){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            File chromeDriver = new File("src\\test\\resources\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
            driver = new ChromeDriver(options);
        }
        else{
	        throw new Error("Unsupported Browser");
        }
		baseUrl = properties.getProperty("baseUrl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//navigationHelper = new NavigationHelper(this);
		//groupHelper = new GroupHelper(this);
		//contactHelper = new ContactHelper(this);
        driver.get(baseUrl);
	}
	
	public void stop() {
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
