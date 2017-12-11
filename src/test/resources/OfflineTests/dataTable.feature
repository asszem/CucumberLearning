Feature: discovering how dataTables in Gherkin works 
    Sources: http://www.thinkcode.se/blog/2014/06/30/cucumber-data-tables


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
    When Joe changes his country to 999 
    Then the table should look like this 
        |country	|customer_number|customer_name	|
        |999		|123456			|Joe			|
        |123		|654321			|Bill			|
        |760		|000000			|Klara			|
        
Scenario: using lists without DataTables 
    Given a list of numbers 
        | 17   |
        | 42   |
        | 4711 |
    When I summarize them 
    Then should I get 4770

@WIP
Scenario: using HashMap for 3 column table with header
    Given This is the hash map to get values
      | product | currency | price |
      | coffee  | EUR      | 1     |
      | donut   | HUF      | 18    |
	