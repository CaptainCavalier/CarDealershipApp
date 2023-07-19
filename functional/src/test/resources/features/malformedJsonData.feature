Feature: testing for a malformed JSON request to a given endpoint

  Scenario: the client sends a malformed Json request to the /cars/admin endpoint and the error is caught by our global exception handler.
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with a malformed json list
      Then the response should have a status code of 400
      And the response body should have the key '{"Description":"Incorrect car data provided"}'
