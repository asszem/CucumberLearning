package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import drivers.DriverLoader;

public class Hooks {

	@Before // This is a CUCUMBER and not a Junit annotation! And this runs before EVERY test, not just this one
	public void setupBrowser() {
		// The DriverLoader class will decide which driver to be loaded by calling the intialize method
		DriverLoader.initializeNewDriver();
	}

	@After
	public void killAllProcessses() {
		 DriverLoader.closeDriver();
//		helpers.KillAllDrivers.killAllDrivers();
//		helpers.KillAllDrivers.killFirefoxBrowsers();
	}
}
