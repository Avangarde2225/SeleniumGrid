package saucelabsDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Task2TestWithSauceLabs {
    public static final String KEY = "daulet";
    public static final String SECRET = "f098ca38-48d4-4f88-9667-acd7336db9c4";
    public static final String HUB_URL = "https://" + KEY + ":" + SECRET + "@ondemand.saucelabs.com:443/wd/hub";
    private WebDriver driver;
    private URL url;
    private DesiredCapabilities caps;

    @BeforeClass
    public void setup() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("name", "Task 2 test");
        url = new URL(HUB_URL);
    }

    @Test(dataProvider = "capabilitiesProvider")
    public void test(String platform, String browserName, String browserVersion){
        caps.setCapability("platform", platform);
        caps.setCapability("version", browserVersion);
        caps.setCapability("browserName", browserName);
        driver = new RemoteWebDriver(url, caps);
        driver.get("https://google.com");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }


    @DataProvider
    public Object[][] capabilitiesProvider(){
        return new Object[][]{
                {"WIN8", "chrome", "79"},
                {"WIN8", "firefox", "60"},
        };
    }
}
