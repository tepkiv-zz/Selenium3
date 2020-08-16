package tests;

import com.BaseTest;
import com.ImplementIRetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LearnRetryAnalyzer extends BaseTest {
    private static int attempt = 1;

    @Test(retryAnalyzer = ImplementIRetryAnalyzer.class)
    public void learnRetry() throws MalformedURLException {
        if (attempt == 3) {
            attempt = 1;
        } else {
            Assert.fail("Failed on" + (attempt++) + " attempt");
        }
    }
}
