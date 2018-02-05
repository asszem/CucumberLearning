package src.test.java.support;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyWebDriver {
	private EventFiringWebDriver webDriver;

	public EventFiringWebDriver getWebDriver() {
		if (webDriver == null) {
			webDriver = new EventFiringWebDriver(new NicebankFirefoxWebDriver().initializeFirefoxWebDriver());
		}
		return webDriver;
	}
}
