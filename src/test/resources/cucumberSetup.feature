Feature: To check that main tutorial course pages have loaded in TheTestRoom.com 

Background: List all steps here that are the same for every scenario 
	Given I navigate to TheTestRoom.com 
	
Scenario: To check that the Cucumber tutorial main page has loaded 
	When I navigate to Cucumber Tutorial page 
	Then the page title should be visible 

Scenario: To check that the Java WebDriver tutorial main page has loaded 
	When I navigate to Java WebDriver page 
	Then the page title should be visible 
