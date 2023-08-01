Feature: Testing the updateCar method to update a car in the database

  Scenario: the client makes a put request to the cars/admin/put endpoint with an updated car and receives a 200 status code and body of OK
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      | brand | model | year | price  | mileage | colour     |
      | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
      | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
    When the client sends a "PUT" request to "/cars/admin/put" endpoint to update a car with the following:
      | id | brand | model | year | price  | mileage | colour |
      | 1  | BMW   | X5    | 2022 | 95000  | 25000   | red    |
    Then the response should have a status code of 200
    And the response body should have the key '{"Description":"Car updated"}'