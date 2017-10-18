@SingleAccount
Feature: Cash Withdrawal 

@HappyPath @WIP @SeparateTagsWithSpace
Scenario Outline: Successful withdrawal from an account in credit 
	Given my account has been credited with $<initialBalance>
	And ATM has sufficient money to dispose
	When I withdraw $<withdrawnMoney>
	Then $<withdrawnMoney> should be dispensed 
	And my account balance should be equal to $<newBalance>
	And "<successMessage>" message should be displayed
	Examples:
	|initialBalance	| withdrawnMoney	| newBalance 	| successMessage |
	|100.00			| 25.00			 	| 75.00			| Successfull withdrawal |
	|50.00			| 5.00			 	| 45.00			| Successfull withdrawal |

@UnhappyPath
Scenario Outline: Unsuccessful withdrawal from an account due to insufficient account balance 
	Given my account has been credited with $<initialBalance>
	And ATM has sufficient money to dispose
	When I withdraw $<withdrawnMoney> 
	Then "<errorMessage>" message should be displayed
	And my account balance should be equal to $<initialBalance>
	Examples:
	|initialBalance	| withdrawnMoney	| errorMessage 									|
	|50.00			| 500.00			| Error: Insufficient funds on account balance  |

@UnhappyPath
Scenario Outline: Unsuccessful withdrawal from an account due to insufficient money in ATM machine
	Given my account has been credited with $<initialBalance>
	And ATM does not have sufficient money to dispose
	When I withdraw $<withdrawnMoney> 
	Then "<errorMessage>" message should be displayed
	And my account balance should be equal to $<initialBalance>
	Examples:
	|initialBalance	| withdrawnMoney	| errorMessage							|
	|50.00			| 25.00				|Error: ATM does not have enough money|