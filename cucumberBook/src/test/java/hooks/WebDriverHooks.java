package src.test.java.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import src.test.java.support.Helper;

public class WebDriverHooks {
	private Helper helper;

	public WebDriverHooks(Helper helper) {
		this.helper = helper;
	}

//	@After
	public void finish(Scenario scenario) {
		try {
			System.out.println("Finish hook called, screenshot taken");
			byte[] screenshot = helper.getWebDriver().getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		} finally {
			// Firefox crashes
			// helper.getWebDriver().close();
		}
	}
}
