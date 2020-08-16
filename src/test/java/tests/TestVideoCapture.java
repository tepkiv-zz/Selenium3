package tests;

import com.BaseTest;
import com.listener.VideoListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(VideoListener.class)
public class TestVideoCapture extends BaseTest {

    @Test
    public void testVideoCapture() throws MalformedURLException {
        URL url = new URL("http://jqueryui.com/droppable/");
        Actions actions = new Actions(driver);

        open(url.toString());

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement droppable = driver.findElement(By.id("droppable"));
        WebElement draggable = driver.findElement(By.id("draggable"));

        actions.dragAndDrop(draggable, droppable).perform();

        driver.switchTo().defaultContent();
    }
}
