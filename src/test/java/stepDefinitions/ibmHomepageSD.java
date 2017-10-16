package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.ChromeWebDriver;
import drivers.FirefoxWebDriver;

public class IbmHomepageSD {

	WebDriver driver;

	// TODO create a PageObject for IBM com and move the Driver to there, extend this class to there

	@Given("^I start \"([^\"]*)\"$")
	public void i_start(String browser) throws Throwable {
		System.out.println("Starting test with browser " + browser);
		switch (browser.toLowerCase()) {
		case "firefox":
			driver = new FirefoxWebDriver().initializeFirefoxWebDriver();
			break;
		case "chrome":
			driver = new ChromeWebDriver().initializeChromeWebDriver();
			break;
		}
	}

	@Given("^I open \"([^\"]*)\"$")
	public void i_open_ibm_com(String url) throws Throwable {
		driver.get(url);
	}

	// Warnings are in an iframe that needs to be handled
	// http://toolsqa.com/selenium-webdriver/handling-iframes-using-selenium-webdriver/
	@Given("^I accept the cookie warning$")
	public void i_accept_the_cookie_warning() throws Throwable {
		System.out.println("Cookie warning step started...");

		// Finding ALL iFrames By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));

		// Walk through all available iframes to verify whether cookie accept popup is present
		System.out.println("All available iframes");
		for (WebElement element : iframeElements) {
			String currentIframeID = element.getAttribute("id");
			System.out.println("iframe ID:  " + currentIframeID);

			// Only execute this step if the iframe with cookie warning is presented
			String cookieAcceptiframeID = "pop-frame09112811852654618";
			if (currentIframeID.equals(cookieAcceptiframeID)) {
				WebElement iframeID = driver.findElement(By.id(currentIframeID));
				System.out.println("Switching to iframe" + iframeID);
				driver.switchTo().frame(iframeID);

				// Finding and clicking the Accept button
				String xPath = "/html/body/div[8]/div[1]/div/div[4]/a[1]";
				WebElement acceptButton = driver.findElement(By.xpath(xPath));
				acceptButton.click();
				System.out.println("Switching back to main page");

				// Switching back to main page
				driver.switchTo().defaultContent();
			}
		}//end iframe for-each
	}//end test

	@Given("^I click on Marketplace button$")
	public void i_click_on_Marketplace_button() throws Throwable {
		driver.findElement(By.linkText("Marketplace")).click();
	}

	@Given("^Marketplace page loads$")
	public void marketplace_page_loads() throws Throwable {
		String marketPlaceXPath = "//*[@id=\"ibm-top\"]/div[3]/div[1]/a";
		WebDriverWait waiter = new WebDriverWait(driver, 15); // timeout in seconds
		waiter.until(new ExpectedCondition<Boolean>() {

			// Selenium waits until this returns true or timeout exceeded
			public Boolean apply(WebDriver driver) {
				// This only works with FireFox driver
				// return driver.findElement(By.tagName("title")).getText().contains("IBM Marketplace - United Kingdom");

				String marketPlaceXPath = "//*[@id=\"ibm-top\"]/div[3]/div[1]/a";
				return driver.findElement(By.xpath(marketPlaceXPath)).getText().contains("Marketplace");
			}
		}); // end of anonymous class
	}

	@When("^I click to \"([^\"]*)\" field$")
	public void i_click_to_field(String arg1) throws Throwable {
		driver.findElement(By.cssSelector("input[placeholder='Search IBM Marketplace']")).click();
		;
	}

	@When("^I enter \"([^\"]*)\"$")
	public void i_enter(String keyEntered) throws Throwable {
		driver.findElement(By.cssSelector("input[placeholder='Search IBM Marketplace']")).sendKeys(keyEntered);
	}

	@When("^I click on the Search button$")
	public void i_click_on_the_Search_button() throws Throwable {
		driver.findElement(By.className("search--submit")).click();
	}

	@Then("^The result should include the \"([^\"]*)\" page$")
	public void the_result_should_include_the_page(String expectedResult) throws Throwable {
		System.out.println("Result page verification step started...");

		// Locate reusutl by xPath
		String resultXPath = ".//*[@id='mg-hits-wrapper']/div[1]/article/a/h2";
		WebElement result = driver.findElement(By.xpath(resultXPath));
		System.out.println("Trying xpath..." + result.getText());

		// Wait until page loads
		WebDriverWait waiter = new WebDriverWait(driver, 15); // timeout in seconds
		ExpectedCondition<Boolean> condition = ExpectedConditions.attributeToBe(By.xpath(resultXPath), "class",
				"offering--name ibm-bold ibm-padding-bottom-0 ibm-linkcolor-default");
		waiter.until(condition);

		// Make assertion
		assertEquals(expectedResult, result.getText());

		// FAILED ATTEMPTS

		// By class name of result h2 tag
		// String resultClassName="offering--name ibm-bold ibm-padding-bottom-0 ibm-linkcolor-default";
		// //Listing all elements for resultClassName
		// int counter=0;
		// for (WebElement element:driver.findElements(By.className(resultClassName))){
		// System.out.println("Class text= "+element.getText());
		// counter++;
		// }
		// System.out.println("Found elements: "+counter);

		// By Class name of page size options
		// **********************
		// for (WebElement element:driver.findElements(By.tagName("class"))){
		// System.out.println("Class name:" + element.getText());
		// }
		//
		// String classNameOfPageSize="mg-pagesize-option__text";
		// ExpectedCondition<WebElement> condition = ExpectedConditions.presenceOfElementLocated(By.className(classNameOfPageSize));
		// System.out.println("Condition status="+condition.apply(driver));
		// waiter.until(condition);
		// System.out.println("Condition passed (elvileg)");

		// By checking h2 tag text()
		// **********************
		// ExpectedCondition<List<WebElement>> condition = ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("h2"));
		// System.out.println("Condition status="+condition.apply(driver));
		// List<WebElement> h2tags = driver.findElements(By.tagName("h2"));
		// System.out.println("Listing all h2 tags");
		// for (WebElement h2tag:h2tags){
		// System.out.println("h2 tag:" + h2tag.getText());
		// }
		// waiter.until(new ExpectedCondition<Boolean>() {
		// public Boolean apply(WebDriver driver) {
		// //Does not work
		// // boolean status = driver.findElement(By.tagName("h2")).getText().contains("z14");
		// // String h2Text = driver.findElement(By.tagName("h2")).getText();
		// // System.out.println("h2 text=" + h2Text);
		// // System.out.println("h2 contains z14=" + status);
		//
		// //Does work, but the test fails
		//// boolean status = driver.getTitle().contains("IBM Product Catalog Search");
		//// System.out.println("Status="+status);
		// String resultXpath=".//*[@id='mg-hits-wrapper']/div[1]/article/a/h2";
		// WebElement status=driver.findElement(By.xpath(resultXpath));
		// System.out.println("Status="+status.isDisplayed());
		// System.out.println("text of xpath" + status.getText());
		// return status.isDisplayed();
		// }
		// });

		// By Sleeping the thread
		// **********************
		// System.out.println("Main thread sleeps");
		// Thread.sleep(10000); //10 sec
		// System.out.println("Main thread wakes, assertion starts");
		// Locating the element by h2 tag does not work
		// String h2Text = driver.findElement(By.tagName("h2")).getText();
		// System.out.println("h2 text=" + h2Text);
		// boolean status = driver.findElement(By.tagName("h2")).getText().contains("z14");
		// System.out.println("After wait: h2 contains z14=" + status);

	}

}
