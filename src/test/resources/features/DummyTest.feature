
#TODO - More configuration required

Feature: Dummy feature

  Background:
    This is a dummy feature file
    Feel free to add any test for practice purpose

  @UI
  Scenario: UI testing
    Given User opens tutorials point webpage
    When User fills the form
      |firstName|lastName|username|password|
      |john|doe|john.doe@test.com|0123456789|
      |jacob|seed|jacob.seed@test.com|1234567890|
      |john|seed|john.seed@test.com|234687541|
      |hannah|seed|hannah.seed@test.com|556847123|
    Then User clicks on register button

  @UI
  Scenario Outline: UI testing2 - using scenario outline and examples (similar to TestNG DataProvider annotation)
    Given User opens tutorials point webpage
    When User enters "<firstName>", "<lastName>", "<username>", "<password>"
    Then User clicks on register button
    Examples:
      | firstName | lastName | username | password |
      |john|doe|john.doe@test.com|0123456789|
      |jacob|seed|jacob.seed@test.com|1234567890|
      |john|seed|john.seed@test.com|234687541|
      |hannah|seed|hannah.seed@test.com|556847123|

  @API
 Scenario: API testing
    Given User invokes a rest call
    When User performs GET call for "3"
    Then User gets the book details