Feature: testing add car endpoint

  Scenario: a client makes a post request to the add cars/admin endpoint and receives a 201 status and body of "Database Updated"
  Given the body of the car model is
    | brand | model | year | price  | mileage | colour     |
    | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
    | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
    When client sends a "POST" request to "/cars/admin/post" endpoint
    Then the response should have a status code of 201
    And the response body should have the key '{"Description":"Database Updated"}'


