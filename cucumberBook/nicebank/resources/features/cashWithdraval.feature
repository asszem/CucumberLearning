@NicebankTag1 
Feature: Cash Withdrawal 

@NicebankTag2 
Scenario: Successful withdrawal from an account in credit 
	Given I have deposited $123.45 in my account 
	When I withdraw $23.45 
	Then $23.45 should be dispensed 