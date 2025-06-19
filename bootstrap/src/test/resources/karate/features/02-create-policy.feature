Feature: Create policy

  Scenario: Create new policy
    Given url baseUrl
    And path 'policies'
    And request
    """
    {
      "name": "Test Policy",
      "startDate": "2024-01-01T00:00:00Z",
      "endDate": "2025-01-01T00:00:00Z",
      "status": "ACTIVE"
    }
    """
    When method post
    Then status 201


    * match response.id == '#number'
    * match response.name == 'Test Policy'
    * match response.status == 'ACTIVE'
    * match response.startDate == '2024-01-01T00:00:00Z'
    * match response.endDate == '2025-01-01T00:00:00Z'
    * match response.createdAt == '#string'
    * match response.updatedAt == '#string'


