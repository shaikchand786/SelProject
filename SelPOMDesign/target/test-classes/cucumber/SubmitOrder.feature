@tag
Feature: Purchase the products from Rahul Shetty Academy website
  I want to use this template for my feature file.
  
	Background:
	Given I landed on Rahul Shetty Academy website
		
  @tag1
  Scenario Outline: Positive scenario for submitting the order
    Given Loggedin with username <username> and password <password>
    When I add the product <productname> to cart
    And checkout the productname <productname> and clickon submit order button
    Then select the countryname <countryname> from dropdown in Place Order page 
    And clickon Place Order button
    Then check place order message as "THANKYOU FOR THE ORDER."
    
    Examples: 
      | username  					| password | productname  	|	countryname	|
      | dummy786@gmail.com 	| !T34567t | IPHONE 13 PRO 	|	India				|
