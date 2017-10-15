package drivers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class FirefoxWebDriver {

	// TODO export these to an external config file and read it here
	long impliciteTimeout = 10;
	long pageLoadTimeout = 15;
	TimeUnit timeUnit = TimeUnit.SECONDS;
	boolean marionette = true;

	// Method to actually initialize the driver once the FirefoxWebDriver object is created
	public WebDriver initializeFirefoxWebDriver() {

		//Set Marionette's log level TODO find out why this doesnt work
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("log", "{level: info}");
		options.setLogLevel(Level.INFO);

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", marionette);
		capabilities.setCapability("moz:firefoxOptions", options);
//		capabilities.setCapability("moz:firefoxOptions: {log: {level: info}}");


		System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriverX64.exe");

		WebDriver driver = new FirefoxDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(impliciteTimeout, timeUnit);

		
		// Throws InvalidArgumentException for geckodriver. Works for chromedriver
		// driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, timeUnit);

		System.out.println("Driver: FirefoxDriver");
		System.out.println("GeckoDriver: Windows GeckoDriverX64");
		System.out.println("SetCapability marionette: " + marionette);
		System.out.println("Implicite timeout: " + impliciteTimeout + " " + timeUnit.toString());
		// System.out.println("Pageload timeout: " + pageLoadTimeout + " " + timeUnit.toString());
		return driver;
	}
	
	public void killall() {
		String command = "taskkill /im geckodriverX64.exe /F";
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
