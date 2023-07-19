Feature: testing get all cars endpoint

  Scenario: the client makes a get request to the cars/admin endpoint and receives a 200 status code and body containing a list of cars
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      | brand | model | year | price  | mileage | colour     |
      | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
      | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
    When client sends a "GET" request to "/cars/admin/get" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      | brand | model | year | price  | mileage | colour     |
      | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
      | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |

