Feature: Multiple checkout scenarios
	Scenario: Checkout only one banana
		Given the price of a "banana" is 40c
		When I checkout 1 "banana"
		Then the total price should be 40c
	Scenario Outline: Checkout lots of bananas
		Given the price of a "<itemName>" is 40c
		When I checkout <count> "<itemName>"
		Then the total price should be <total>c
		Examples:
			|count	|total	| itemName	|
			|1		|40		| banana	|
			|2		|80		| alma		|
			|3		|120	| apple		|
	Scenario: A banana and an apple
		Given the price of a "banana" is 40c
		And the price of a "apple" is 25c
		When I checkout 1 "banana"
		And I checkout 1 "apple"
		Then the total price should be 65c
	Scenario: Checkout two bananas separately 
		Given the price of a "banana" is 40c
		When I checkout 1 "banana"
		And I checkout 1 "banana"
		Then the total price should be 80c
	Scenario: Checkout two bananas simultaneously 
		Given the price of a "banana" is 40c
		When I checkout 10 "banana"
		Then the total price should be 400c
