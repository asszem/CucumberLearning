@SingleAccount
Feature: Display Balance

@HappyPath @WIP
Scenario Outline: Displaying the balance of the user account
	Given my account has been credited with $<initialBalance>
	When I click on the Display Balance button
	Then My account balance of $<initialBalance> should be displayed
	Examples:
	|initialBalance	| 
	|100.00			|
#	|50.00			| 
#	|22.00			|