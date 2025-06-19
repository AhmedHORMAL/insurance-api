Feature: Get a policy by ID

  Background:
    * call read('classpath:karate/features/02-create-policy.feature')
    * def created = response
    * def id = created.id

  Scenario: Retrieve the created policy
    Given url baseUrl
    And path 'policies', id
    When method get
    Then status 200
    And match response.id == id
    And match response.name == "Test Policy"
