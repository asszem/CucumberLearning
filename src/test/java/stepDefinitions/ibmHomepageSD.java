package stepDefinitions;

import org.openqa.selenium.WebDriver;

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
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^Marketplace page loads$")
	public void marketplace_page_loads() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click to \"([^\"]*)\" field$")
	public void i_click_to_field(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
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
