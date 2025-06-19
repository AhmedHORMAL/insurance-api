Feature: Get all policies

  Scenario: Create 2 policies and retrieve them

    Given url baseUrl
    And path 'policies'
    And request
    """
    {
      "name": "Policy A",
      "status": "ACTIVE",
      "startDate": "2024-01-01T00:00:00Z",
      "endDate": "2025-01-01T00:00:00Z"
    }
    """
    When method post
    Then status 201

    Given url baseUrl
    And path 'policies'
    And request
    """
    {
      "name": "Policy B",
      "status": "ACTIVE",
      "startDate": "2023-06-01T00:00:00Z",
      "endDate": "2024-06-01T00:00:00Z"
    }
    """
    When method post
    Then status 201

    # Get all policies
    Given url baseUrl
    And path 'policies'
    When method get
    Then status 200

    * print response

    * match response contains deep
    """
    [
      { name: 'Policy A', status: 'ACTIVE', startDate: '2024-01-01T00:00:00Z', endDate: '2025-01-01T00:00:00Z' },
      { name: 'Policy B', status: 'ACTIVE', startDate: '2023-06-01T00:00:00Z', endDate: '2024-06-01T00:00:00Z' }
    ]
    """
