package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.FirefoxWebDriver;

public class ibmHomepageSD {
	// TODO write the Driver implementation
	WebDriver driver = new FirefoxWebDriver().initializeFirefoxWebDriver();

	// TODO create a PageObject for IBM com and move the Driver to there, extend this class to there

	@Given("^I open \"([^\"]*)\"$")
	public void i_open_ibm_com(String url) throws Throwable {
		driver.get(url);
	}

	@Given("^I click on Marketplace button$")
	public void i_click_on_Marketplace_button() throws Throwable {
		driver.findElement(By.linkText("Marketplace")).click();
	}

	@Given("^Marketplace page loads$")
	public void marketplace_page_loads() throws Throwable {
		WebDriverWait waiter = new WebDriverWait(driver, 5); // timeout in seconds
		waiter.until(new ExpectedCondition<Boolean>() {

			// Selenium waits until this returns true or timeout exceeded
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.tagName("title")).getText().equals("IBM Marketplace - United Kingdom");
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
