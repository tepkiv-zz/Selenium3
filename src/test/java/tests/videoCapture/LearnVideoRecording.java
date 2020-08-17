package tests.videoCapture;

import com.LocalDesiredCapabilities;
import conf.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class LearnVideoRecording {

    private WebDriver driver;
    private VideoCreator screencaster;
    private Thread videoThread;

    @BeforeClass
    public void startDriver() {
        LocalDesiredCapabilities localDesiredCapabilities = new LocalDesiredCapabilities();
        File chromeDriver = new File("src/test/resources/drivers/chromedriver.exe");
        System.setProperty(TestProperties.CHROME_DRIVER, chromeDriver.getAbsolutePath());
        driver = new ChromeDriver(localDesiredCapabilities.chrome());
    }

    @BeforeMethod
    public void setUp(Method m) {
        File videoFile = new File(m.getName() + ".flv");
        screencaster = new VideoCreator(videoFile);
        videoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                screencaster.createVideoFromScreens();
            }
        });
        videoThread.start();
    }

    @Test
    public void test1() {
        driver.get("http://www.seleniumhq.org/");
    }

    @Test
    public void test2() {
        driver.get("http://selenium2.ru/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        screencaster.setPleaseStop(true);
        while (!screencaster.isStoppedCreation()) {
            Thread.sleep(500);
        }
        videoThread.join();
    }


    @AfterClass(alwaysRun = true)
    public void stopDriver() {
        driver.quit();
    }

}
