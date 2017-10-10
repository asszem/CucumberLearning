Feature: Testing how Cucumber works on the IBM.com webpage

Scenario: Clicking on the Marketplace button and entering a z14 search
Given I open "https://ibm.com" 
And I click on Marketplace button
And Marketplace page loads
When I click to "Search IBM Marketplace" field
And I enter "z14"
And I click on the Search button
Then The result should include the "IBM z14" page
