package tests;

import com.BaseTest;
import conf.TestProperties;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

import java.io.File;

public class LeaarnProxy extends BaseTest {

    @Test
    public void testProxy() {
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // Selenium or HTTP client configuration goes here
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);

        File chromeDriver = new File(properties.getProperty(TestProperties.CHROME_DRIVER_PATH));
        System.setProperty(TestProperties.CHROME_DRIVER, chromeDriver.getAbsolutePath());
        WebDriver driver = new ChromeDriver(chromeOptions);


        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar("yahoo.com");

        // open yahoo.com
        driver.get("http://yahoo.com");

        // get the HAR data
        Har har = proxy.getHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }


}
