Feature: Testing how Cucumber works on the IBM.com webpage 

Scenario Outline: Clicking on the Marketplace button 
	and entering search term to the search field on Marketplace page
	Given I start "<browser>" 
	Given I open "https://ibm.com" 
	Given I accept the cookie warning
	And I click on Marketplace button 
	And Marketplace page loads 
	When I click to "Search IBM Marketplace" field 
	And I enter "<searchTerm>" 
	And I click on the Search button 
	Then The result should include the "<searchResult>" page 
	

	Examples: 
		| browser |searchTerm	|searchResult	|
		| firefox |z14			|IBM z14		|
#		| chrome  |z13			|IBM z13	|	
		#The below will fail as the result is located by xpath and not by content|
		#|z13		|IBM z Systems Solution Edition for SAP Applications		|  
	
	Examples:
		| browser |searchTerm	|searchResult										|
#		| firefox |z/os			|IBM Tivoli NetView for z/OS						|
#		| chrome  |windows		|IBM Db2 Merge Backup for Linux UNIX and Windows	|
