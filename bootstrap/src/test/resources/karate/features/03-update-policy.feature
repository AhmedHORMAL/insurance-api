Feature: Update a policy

  Scenario: Update a previously created policy
    Given url baseUrl
    And path 'policies'
    And request
    """
    {
      "name": "Test Policy",
      "status": "ACTIVE",
      "startDate": "2024-01-01T00:00:00Z",
      "endDate": "2025-01-01T00:00:00Z"
    }
    """
    When method post
    Then status 201
    * def createdId = response.id

    Given url baseUrl
    And path 'policies', createdId
    And request
    """
    {
      "name": "Assurance 1",
      "status": "INACTIVE",
      "startDate": "2025-01-01T00:00:00Z",
      "endDate": "2026-01-01T00:00:00Z"
    }
    """
    When method put
    Then status 200

    * match response ==
    """
    {
      id: '#number',
      name: 'Assurance 1',
      status: 'INACTIVE',
      startDate: '2025-01-01T00:00:00Z',
      endDate: '2026-01-01T00:00:00Z',
      createdAt: '#string',
      updatedAt: '#string'
    }
    """
