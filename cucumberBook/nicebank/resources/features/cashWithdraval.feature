@SingleAccount
Feature: Cash Withdrawal 

@HappyPath
Scenario Outline: Successful withdrawal from an account in credit 
	Given I have deposited $<initialBalance> in my account 
	When I withdraw $<withdrawnMoney>
	And ATM has sufficient money to dispose
	Then $<withdrawnMoney> should be dispensed 
	And my account balance should be equal to $<newBalance>
	Examples:
	|initialBalance	| withdrawnMoney	| newBalance 	|
	|100.00			| 25.00			 	| 75.00			|
	|50.00			| 5.00			 	| 45.00			|

@UnhappyPath
Scenario Outline: Unsuccessful withdrawal from an account due to insufficient account balance 
	Given I have deposited $<initialBalance> in my account 
	And ATM has sufficient money to dispose
	When I withdraw $<withdrawnMoney> 
	Then "<errorMessage" message should be displayed
	And my account balance should be equal to $<initialBalance>
	Examples:
	|initialBalance	| withdrawnMoney	| errorMessage 									|
	|50.00			| 500.00			| Error: Insufficient funds on account balance  |

@UnhappyPath
Scenario Outline: Unsuccessful withdrawal from an account due to insufficient money in ATM machine
	Given I have deposited $<initialBalance> in my account 
	When I withdraw $<withdrawnMoney> 
	And ATM does not have sufficient money to dispose
	Then "<errorMessage" message should be displayed
	And my account balance should be equal to $<initialBalance>
	Examples:
	|initialBalance	| withdrawnMoney	| errorMessage							|
	|50.00			| 25.00				|Error: ATM does not have enough money	|