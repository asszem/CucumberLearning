@EntireFeature_tag
Feature: To check that main tutorial course pages have loaded in TheTestRoom.com 

Background: List all steps here that are the same for every scenario 
	Given I navigate to TheTestRoom.com 
	And I click on the No Thanks button if newsletter window is displayed

@Tag1_CucumberPage	
Scenario: To check that the Cucumber tutorial main page has loaded 
	When I click to Cucumber Tutorial page 
	Then the page title should contain "Cucumber"

@Tag2_WebDriverPage
Scenario: To check that the Java WebDriver tutorial main page has loaded 
	When I click to Java WebDriver page 
	Then the page title should contain "Java WebDriver"
