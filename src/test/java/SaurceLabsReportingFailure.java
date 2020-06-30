import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SaurceLabsReportingFailure {
    public static final String USERNAME = "serman2225";
    public static final String KEY = "a2e321bb-7aa6-4a21-ae08-adc0357a43b0";
    private WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("safari");
        cap.setPlatform( Platform.MAC );
        cap.setCapability("name", "Web Driver demo Test");
        URL url = new URL( "https://" + USERNAME + ":" + KEY + "@ondemand.saucelabs.com:443/wd/hub" );
        driver = new RemoteWebDriver(url, cap);

    }

    @Test
    public void test() throws Exception {

        try {
            driver.get("https://google.com");
            throw new Exception( "Test failed" );
        } catch(Exception e) {
            reportBack(false);
            throw e;
        }

    }

    private void reportBack(boolean passed) {
        SauceREST client = new SauceREST(USERNAME, KEY);
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", passed);
        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        client.updateJobInfo(sessionId, updates);
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
