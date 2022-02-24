Feature: To add ANZ bank account in Xero organisation
  As a Xero User,
  In order to manage my business successfully,
  I want to be able to add an "ANZ (NZ)" bank account inside any Xero Organisation.

  Scenario: Validate addition of ANZ bank account in xero organisation
    Given open the browser
    And the user logs into the xero organisation
    And the user is in the xero organisation dashboard page
    When the user adds Bank Account details in Bank Account page
    Then the bank account details are added successfully
    And logout from the application and close the browser
    
 # About the framework: 
 # This is a maven project with cucumber BDD framework using selenium webdriver and java language with junit framework
 
 # Steps to execute:
 # right click in AddBankAccount.feature file and click on Run As Cucumber Feature
 

  
    
    