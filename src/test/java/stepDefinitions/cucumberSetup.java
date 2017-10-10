package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class cucumberSetup {
	WebDriver driver;

	@Given("^I navigate to TheTestRoom\\.com$")
	public void i_navigate_to_TheTestRoom_com() throws Throwable {
		System.out.println("Going to TheTestRoom.com");
		System.out.println("Windows GeckoDriverX64 started");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriverX64.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("http://www.thetestroom.com");
	}

	@When("^I navigate to Cucumber Tutorial page$")
	public void i_navigate_to_Cucumber_Tutorial_page() throws Throwable {
		System.out.println("Clicking on Cucumber Tutorial Page");
		driver.findElement(By.linkText("Cucumber")).click();
	}

	@Then("^the page title should be visible$")
	public void the_page_title_should_be_visible_w() throws Throwable {
		System.out.println("Checking page title");
		System.out.println(driver.getTitle());
//		driver.close();
	}

}
