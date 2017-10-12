Feature: discovering how dataTables in Gherkin works 
Scenario: A data table with headers in the top row 
	Given customers are: 
		|country	|customer_number|customer_name	|
		|897		|123456			|Joe			|
		|123		|654321			|Bill			|
		|760		|000000			|Tibor			|
	When Tibor changes his name to Klara 
	Then the table should look like this 
		|country	|customer_number|customer_name	|
		|897		|123456			|Joe			|
		|123		|654321			|Bill			|
		|760		|000000			|Klara			|