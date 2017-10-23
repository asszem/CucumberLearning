package drivers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeWebDriver {

	// TODO export these to an external config file and read it here
	private static String OS = System.getProperty("os.name").toLowerCase();
	long impliciteTimeout = 10;
	long pageLoadTimeout = 15;
	TimeUnit timeUnit = TimeUnit.SECONDS;
	boolean marionette = false;

	// Method to actually initialize the driver once the FirefoxWebDriver object is created
	public WebDriver initializeChromeWebDriver() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability("marionette", marionette);

		System.out.println("OS detected: " + OS);
		if (OS.contains("mac")) {
			// System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/osx/chromedriver");
			// options.addArguments("--kiosk");
		} else if (OS.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
		} else if (OS.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", "/home/andras/webdrivers/chromedriver");
		}
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(impliciteTimeout, timeUnit);

		// Throws InvalidArgumentException for geckodriver. Works for chromedriver
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, timeUnit);

		System.out.println("Driver: ChromeDriver");
		// System.out.println("SetCapability marionette: " + marionette);
		System.out.println("Implicite timeout: " + impliciteTimeout + " " + timeUnit.toString());
		System.out.println("Pageload timeout: " + pageLoadTimeout + " " + timeUnit.toString());
		return driver;
	}

	public void killall() {
		String command = "taskkill chromedriver";
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
