package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
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
		driver.findElement(By.cssSelector("input[placeholder='Search IBM Marketplace']"));
	}

	@When("^I enter \"([^\"]*)\"$")
	public void i_enter(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click on the Search button$")
	public void i_click_on_the_Search_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The result should include the \"([^\"]*)\" page$")
	public void the_result_should_include_the_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
