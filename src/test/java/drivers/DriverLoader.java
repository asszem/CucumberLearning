package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverLoader {

	private static DriverLoader instance = new DriverLoader();
	public static WebDriver driver;

	// This method to be called from Hooks to initialize a driver with @Before annotation
	public static DriverLoader initializeNewDriver() {
		// This is the part where the actual driver needs to be decided

		 driver = new FirefoxWebDriver().getFirefoxWebDriver();

//		 driver = new ChromeWebDriver().initializeChromeWebDriver();

		return instance; // Instantiate DriverLoader
	}

	// Getter for StepDefinitions to get the driver
	public static WebDriver getDriver() {
		return driver;
	}

	// To be called from @After
	public static void closeDriver() {
		// driver.close(); // Commented out because Firefox keeps crashing when this command is issued
		// driver=null;
	}

}
