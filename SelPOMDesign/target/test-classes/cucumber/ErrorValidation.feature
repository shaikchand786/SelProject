
@tag
Feature: Error validation of your page
  I want to use this template for my feature file

  @Regression
	  Scenario Outline: Positive scenario for submitting the order
	  Given I landed on Rahul Shetty Academy website
    When Loggedin with username <username> and password <password>
    Then check error message as <errormessage>
    
    Examples: 
      | username  					| password |	errormessage									|
      | dummy78@gmail.com 	| !T34567t |	Incorrect email or password.	|