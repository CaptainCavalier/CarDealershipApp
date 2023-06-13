Feature: testing private endpoint

  Scenario: the client makes a get request to the private/status endpoint and receives a 200 status code and body of OK
    When client sends a "GET" request to "/private/status" endpoint
    Then the response should have a status code of 200
    And the response body should contain 'OK'
