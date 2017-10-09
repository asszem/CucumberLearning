Feature: Multiple checkout scenarios
	Background:
		# This is a comment. The Background Given part will be applied to every scenario
		Given the price of a "banana" is 40c
	Scenario: Checkout only one banana
		# In this scenario the background context of price is overwritten
		Given the price of a "banana" is 20c
		When I checkout 1 "banana"
		Then the total price should be 20c
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
		In this scenario I also test
		Whether I can really put multiple lines
		As a Scenario description
		Given the price of a "banana" is 40c
		When I checkout 1 "banana"
		# At this point the add method is called again, the total must remember the first add
		And I checkout 1 "banana"
		Then the total price should be 80c
	Scenario: Checkout two bananas simultaneously (no Given keyword used) 
		When I checkout 10 "banana"
		Then the total price should be 400c
