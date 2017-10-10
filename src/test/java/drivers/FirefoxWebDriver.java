package drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class FirefoxWebDriver {

	// Method to actually initialize the driver once the FirefoxWebDriver object is created
	public WebDriver initializeFirefoxWebDriver() {

		System.out.println("Windows GeckoDriverX64 started");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriverX64.exe");

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		//capabilities.setCapability("marionette", false); 

		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
