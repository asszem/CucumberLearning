Feature: To check that main tutorial course pages have loaded in TheTestRoom.com

Scenario: To check that the WebDriver Cucumber tutorial main page has loaded
Given I navigate to TheTestRoom.com
When I navigate to Cucumber Tutorial page
Then the page title should be visible:w
