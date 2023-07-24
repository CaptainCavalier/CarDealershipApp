Feature: To check exception handlers are thrown when invalid data is used within the query parameter

  Scenario: A client makes a GET request to the cars/admin/brand?brand=X endpoint with invalid query parameter. They receive a 400 status code and body of "Incorrect query parameter provided"
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | BMW     | X5          | 2000 | 50000  | 80000   | black      |
      | Bentley | Continental | 2010 | 100000 | 10000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | BMW     | X6          | 2023 | 100000 | 1000    | sky blue   |
    When client sends a "GET" request to "/cars/admin/brand?brand=" endpoint
    Then the response should have a status code of 400
    And the response body should have the key '{"Description":"Incorrect query parameter provided"}'