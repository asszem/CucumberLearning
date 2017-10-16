package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.DriverLoader;

public class CucumberSetupSD extends DriverLoader {

	// WebDriver driver = DriverLoader.getDriver();

	@Given("^I navigate to TheTestRoom\\.com$")
	public void i_navigate_to_TheTestRoom_com() throws Throwable {
		System.out.println("Going to TheTestRoom.com");
		driver.navigate().to("http://www.thetestroom.com");
	}

	@When("^I click to Cucumber Tutorial page$")
	public void i_navigate_to_Cucumber_Tutorial_page() throws Throwable {
		System.out.println("Clicking on Cucumber Tutorial Page");
		driver.findElement(By.linkText("Cucumber")).click();
	}

	@Then("^the page title should be visible$")
	public void the_page_title_should_be_visible_w() throws Throwable {
		System.out.println("Page title is: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Java WebDriver Archives"));
	}

	@When("^I click to Java WebDriver page$")
	public void i_navigate_to_Javam_WebDriver_page() throws Throwable {
		WebElement element = driver.findElement(By.linkText("Java WebDriver"));
		// Actions actions = new Actions(driver);
		// actions.moveToElement(element).click().perform();
		WebDriverWait waiter = new WebDriverWait(driver, 10);
		waiter.until(ExpectedConditions.elementToBeClickable(element));

		// Not working under ChromeDriver. Working under Firefox driver
		// element.click();
		element.sendKeys(Keys.RETURN);
		waiter.until(ExpectedConditions.titleContains("Archives"));

	}

}
