Feature: to test the exception handler for the delete endpoint

    Scenario: A client sends a delete request to the cars/admin endpoint without an ID and receives a 400 status code with a response
        Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
            | brand | model | year | price  | mileage | colour     |
            | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
            | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
        When client sends a "DELETE" request to "/cars/admin/" endpoint
        Then the response should have a status code of 400
        And the response body should have the key '{"description":"Incorrect id provided"}'

    Scenario: A client sends a delete request to the cars/admin endpoint with an incorrect ID and receives a 400 status code with a response
        Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
            | brand | model | year | price  | mileage | colour     |
            | BMW   | X5    | 2022 | 80000  | 10000   | space grey |
            | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
        When client sends a "DELETE" request to "/cars/admin/3" endpoint
        Then the response should have a status code of 400
        And the response body should have the key '{"description":"Incorrect id provided"}'