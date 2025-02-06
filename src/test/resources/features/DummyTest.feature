
#TODO - More configuration required

Feature: Dummy feature

  @UI
  Scenario: UI testing
    Given User opens tutorials point webpage
    When User enters first and last names
      |first|Jane|
      |last|Doe|
    Then User clicks on register button

  @API
  Scenario: API testing
    Given User invokes a rest call
    When User performs GET call for "3"
    Then User gets the book details