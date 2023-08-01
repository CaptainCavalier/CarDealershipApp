Feature: To check exception handlers are thrown when invalid data is used within the query parameter

  Scenario: A client makes a GET request to the cars/admin/brand?brand=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/brand?brand=bent ley" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'

  Scenario: A client makes a GET request to the cars/admin/model?model=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/model?model=$%" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'

  Scenario: A client makes a GET request to the cars/admin/year?year=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/year?year=word" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'

  Scenario: A client makes a GET request to the cars/admin/price?price=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/price?price=" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'

  Scenario: A client makes a GET request to the cars/admin/mileage?mileage=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/mileage?mileage=?20?00@" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'

  Scenario: A client makes a GET request to the cars/admin/colour?colour=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    When client sends a "GET" request to "/cars/admin/colour?colour=1234" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'