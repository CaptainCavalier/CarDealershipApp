Feature: Testing all get by attribute endpoints

  Scenario: A client makes a GET request to the cars/admin/brand endpoint. They receive a 200 status code and body containing a list of cars, filtered by brand and sorted by newest to oldest
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | BMW     | X5          | 2000 | 50000  | 80000   | black      |
      | Bentley | Continental | 2010 | 100000 | 10000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | BMW     | X6          | 2023 | 100000 | 1000    | sky blue   |
    When client sends a "GET" request to "/cars/admin/brand?brand=BMW" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      | brand | model | year | price  | mileage |   colour   |
      | BMW   | X6    | 2023 | 100000 | 1000    | sky blue   |
      | BMW   | X3    | 2015 | 33000  | 65000   | space grey |
      | BMW   | X5    | 2000 | 50000  | 80000   | black      |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

  Scenario: A client makes a GET request to the cars/admin/model endpoint. They receive a 200 status code and body containing a list of cars, filtered by model and sorted by least expensive to most expensive
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  | model | year | price  | mileage |   colour   |
      | BMW     | X5    | 2023 | 80000  | 100     | black      |
      | BMW     | X3    | 2010 | 100000 | 10000   | orange     |
      | BMW     | X5    | 2015 | 33000  | 65000   | space grey |
      | BMW     | X5    | 2002 | 5000   | 100000  | sky blue   |
    When client sends a "GET" request to "/cars/admin/model?model=X5" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      |  brand  | model | year | price  | mileage |   colour   |
      | BMW     | X5    | 2002 | 5000   | 100000  | sky blue   |
      | BMW     | X5    | 2015 | 33000  | 65000   | space grey |
      | BMW     | X5    | 2023 | 80000  | 100     | black      |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

  Scenario: A client makes a GET request to the cars/admin/year endpoint. They receive a 200 status code and body containing a list of cars, filtered by year and sorted by brand alphabetically
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Nissan  | Micra       | 2010 | 1000   | 100000  | black      |
      | Bentley | Continental | 2010 | 100000 | 10000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | BMW     | X6          | 2010 | 20000  | 40000   | sky blue   |
    When client sends a "GET" request to "/cars/admin/year?year=2010" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Bentley | Continental | 2010 | 100000 | 10000   | orange     |
      | BMW     | X6          | 2010 | 20000  | 40000   | sky blue   |
      | Nissan  | Micra       | 2010 | 1000   | 100000  | black      |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

  Scenario: A client makes a GET request to the cars/admin/price endpoint. They receive a 200 status code and body containing a list of cars, filtered by price and sorted by newest to oldest
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Nissan  | Micra       | 2010 | 2000   | 100000  | black      |
      | Bentley | Continental | 2005 | 33000  | 10000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | BMW     | X6          | 2020 | 33000  | 40000   | sky blue   |
    When client sends a "GET" request to "/cars/admin/price?price=33000" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | BMW     | X6          | 2020 | 33000  | 40000   | sky blue   |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | Bentley | Continental | 2005 | 33000  | 10000   | orange     |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

  Scenario: A client makes a GET request to the cars/admin/mileage endpoint. They receive a 200 status code and body containing a list of cars, filtered by mileage and sorted by brand alphabetically
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Nissan  | Micra       | 2010 | 1000   | 65000   | black      |
      | Bentley | Continental | 2010 | 100000 | 65000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | BMW     | X6          | 2010 | 20000  | 40000   | sky blue   |
    When client sends a "GET" request to "/cars/admin/mileage?mileage=65000" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Bentley | Continental | 2010 | 100000 | 65000   | orange     |
      | BMW     | X3          | 2015 | 33000  | 65000   | space grey |
      | Nissan  | Micra       | 2010 | 1000   | 65000   | black      |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

  Scenario: A client makes a GET request to the cars/admin/colour endpoint. They receive a 200 status code and body containing a list of cars, filtered by colour and sorted by model alphabetically
    Given the client sends a "POST" request to "/cars/admin/post" endpoint with the following:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Nissan  | Micra       | 2005 | 1000   | 80000   | white      |
      | Bentley | Continental | 2010 | 100000 | 65000   | white      |
      | BMW     | X3          | 2015 | 33000  | 55000   | space grey |
      | BMW     | X6          | 2012 | 20000  | 40000   | white      |
    When client sends a "GET" request to "/cars/admin/colour?colour=white" endpoint
    Then the response should have a status code of 200
    And the response body should contain the list of cars in the database:
      |  brand  |    model    | year | price  | mileage |   colour   |
      | Bentley | Continental | 2010 | 100000 | 65000   | white      |
      | Nissan  | Micra       | 2005 | 1000   | 80000   | white      |
      | BMW     | X6          | 2012 | 20000  | 40000   | white      |
    Then client sends a "DELETE" request to "cars/admin/delete" endpoint

