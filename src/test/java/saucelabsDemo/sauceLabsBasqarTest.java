package saucelabsDemo;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

    public class sauceLabsBasqarTest {

        private WebDriver driver;

        @Test
        public void shouldOpenSafari(Method method) throws MalformedURLException {

            String sauceUserName = System.getenv("serman2225");
            String sauceAccessKey = System.getenv("a2e321bb-7aa6-4a21-ae08-adc0357a43b0!");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("username", sauceUserName);
            capabilities.setCapability("accessKey", sauceAccessKey);
            capabilities.setCapability("browserName", "Safari");
            capabilities.setCapability("platform", "macOS 10.13");
            capabilities.setCapability("version", "11.1");
            capabilities.setCapability("name", method.getName());

            driver = new RemoteWebDriver(new URL("https://serman2225:a2e321bb-7aa6-4a21-ae08-adc0357a43b0@ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities);
            driver.navigate().to("https://test.basqar.mersys.io/");
            Assert.assertTrue(true);
        }


        /**
         * Below we are performing 2 critical actions. Quitting the driver and passing
         * the test result to Sauce Labs user interface. */
        @AfterMethod
        public void cleanUpAfterTestMethod(ITestResult result) {
            ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

        }

        @AfterClass
        public void quit(){
            driver.quit();
        }
    }

