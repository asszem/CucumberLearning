package src.test.java.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import src.test.java.support.MyWebDriver;

public class WebDriverHooks {
	private MyWebDriver webDriver;

	public WebDriverHooks(MyWebDriver myWebDriverInjected) {
		this.webDriver=myWebDriverInjected;
	}

	// @After
	public void finish(Scenario scenario) {
		try {
			System.out.println("Finish hook called, screenshot taken");

			// This works with chromedrier:
			// byte[] screenshot = ((TakesScreenshot) webdriver.getScreenshotAs(OutputType.BYTES);

			byte[] screenshot = webDriver.getWebDriver().getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		} finally {
			// Firefox crashes
			// helper.getWebDriver().close();
		}
	}
}
