Feature: To check exception handlers are thrown when missing/malformed data is sent to the API as a POST request

    Scenario: a client makes a post request to the add cars/admin endpoint with incorrect data and receives a 400 status and a body of "Incorrect car data provided"
      Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
          | brand | model | year | price | mileage | colour   |
          |       | X5    | 2022 | 80000 | 10000   | sky blue |
      Then the response should have a status code of 400
      And the response body should have the key '{"Description":"Incorrect car data provided"}'


    Scenario: a client makes a post request to the add cars/admin endpoint with incorrect data and receives a 400 status and a body of "Incorrect car data provided"
      When the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
        | "" | model | year | price | mileage | colour   |
        | BMW  | X5    | 2022 | 80000 | 10000   | sky blue |
      Then the response should have a status code of 400
      And the response body should have the key '{"Description":"Incorrect car data provided"}'


    Scenario: the client sends a malformed Json request to the /cars/admin endpoint and the error is caught by our global exception handler.
      When the client sends a "POST" request to "/cars/admin/post" endpoint with a malformed json list
      Then the response should have a status code of 400
      And the response body should have the key '{"Description":"Incorrect car data provided"}'




