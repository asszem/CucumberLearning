package drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeWebDriver {
	//TODO export these to an external config file and read it here
	long impliciteTimeout = 10;
	long pageLoadTimeout = 15;
	TimeUnit timeUnit = TimeUnit.SECONDS;
	boolean marionette = false;

	// Method to actually initialize the driver once the FirefoxWebDriver object is created
	public WebDriver initializeChromeWebDriver() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability("marionette", marionette);

		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chrome\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(impliciteTimeout, timeUnit);
		
		//Throws InvalidArgumentException for geckodriver. Works for chromedriver
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, timeUnit);

		System.out.println("Driver: ChromeDriver");
//		System.out.println("SetCapability marionette: " + marionette);
		System.out.println("Implicite timeout: " + impliciteTimeout + " " + timeUnit.toString());
		System.out.println("Pageload timeout: " + pageLoadTimeout + " " + timeUnit.toString());
		return driver;
	}
}
