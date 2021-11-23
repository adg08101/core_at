#Author: ahmed.davila@generalsoftwareinc.com
#Keywords Summary : Some Explanation on Keywords
#Feature: Feature file.
#Scenario:
#------------------------------------------
#1 Go to default URL Page
#2. New line here
#------------------------------------------
#Given: Some precondition step
#------------------------------------------
#1. Preconditions Here
#2. Some more stuff here.
#------------------------------------------
#When: Some key actions
#Then: To observe outcomes or validation
#------------------------------------------
#1. Clicks on continue here.
#------------------------------------------
#And,But:
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for some table
#Background: Given Any user is working
#Sample Feature Definition Template

Feature: Default main Feature
  As a: User
  I Want To: Go to <App> Login Page
  So That: I can Log into <App>

  Background:
  #  Given BackGround Conditions

  Scenario Outline: Main Default Scenario
    Given The user is in "<view>" view
    Then The user LogsIn with "<userName>" and "<password>"
    Then The system saves user and project values
    Then The user LogsOff
    #Then Some more here
    #When Some more here
    #And Some more here

    Examples:
      | view          | userName       | password       |
      | Kahua Login   | kahua_username | kahua_password |
