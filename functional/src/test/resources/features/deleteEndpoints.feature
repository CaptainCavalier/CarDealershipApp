Feature: to test the delete endpoint functionality

    Scenario: A client sends a delete request to the cars/admin endpoint and receives a 204 status code
        Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
            | brand | model | year | price  | mileage | colour     |
            | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
            | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
        When client sends a "DELETE" request to "/cars/admin/1" endpoint
        Then the response should have a status code of 204