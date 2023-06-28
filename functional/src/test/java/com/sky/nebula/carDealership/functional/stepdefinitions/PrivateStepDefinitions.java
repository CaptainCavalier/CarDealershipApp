package com.sky.nebula.carDealership.functional.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.nebula.carDealership.functional.config.datatabletype.Car;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PrivateStepDefinitions {

    Response response;

    String requestData;

    String json;

    private RequestSpecification request = given();

    @DataTableType
    public Car carEntry(Map<String, String> entry) {
        return new Car(
                entry.get("brand"),
                entry.get("model"),
                Integer.parseInt(entry.get("year")),
                Integer.parseInt(entry.get("price")),
                Integer.parseInt(entry.get("mileage")),
                entry.get("colour")
        );
    }

    @When("client sends a {string} request to {string} endpoint")
    public void clientSendsARequestToEndpoint(String requestType, String endpoint) {
        switch (requestType) {
            case "GET": response = request.get(endpoint);
            break;
            case "POST": response = request.post(endpoint);
            break;
            default:
                throw new RuntimeException(requestType + " is not a valid request");
        }
    }

    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @And("the response body should contain {string}")
    public void theResponseBodyShouldContainOK(String bodyMessage) {
        json = response.asString();
        Assertions.assertEquals(bodyMessage, json);
    }

    @Given("the body of the car model is")
    public void theBodyOfTheCarModelIs(List<Car> cars) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);
        request = given().contentType(ContentType.JSON).body(json);
    }

    @Given("I want to post the following json: {string}")
    public RequestSpecification iWantToPostTheFollowingJson(String jsonbody) {
        return given().contentType(ContentType.JSON).body(jsonbody);
    }

    @And("the response body should have the key {string}")
    public void theResponseBodyShouldHaveTheKeyDatabaseUpdated(String bodyMessage) {
            json = response.asString();
            Assertions.assertEquals(bodyMessage, json);
    }

    @And("the database has the following cars")
    public void theDatabaseHasTheFollowingCars(List<Car> cars) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);
        request = given().contentType(ContentType.JSON).body(json);
    }

    @And("the response body should contain the list of cars in the database:")
    public void theResponseBodyShouldContainTheListOfCarsInTheDatabase(List<Car> expectedCars) {
        List<Car> actualCars = response.jsonPath().getList(".", Car.class);

        Assertions.assertEquals(expectedCars.size(), actualCars.size());

        for (int i = 0; i < expectedCars.size(); i++) {
            Car expectedCar = expectedCars.get(i);
            Car actualCar = actualCars.get(i);

            Assertions.assertEquals(expectedCar.getBrand(), actualCar.getBrand());
            Assertions.assertEquals(expectedCar.getModel(), actualCar.getModel());
            Assertions.assertEquals(expectedCar.getYear(), actualCar.getYear());
            Assertions.assertEquals(expectedCar.getPrice(), actualCar.getPrice());
            Assertions.assertEquals(expectedCar.getMileage(), actualCar.getMileage());
            Assertions.assertEquals(expectedCar.getColour(), actualCar.getColour());
        }
        System.out.println(expectedCars);
        System.out.println(actualCars);
    }

//    @Given("the client sends a {string} request to {string} endpoint")
//    public void theClientSendsARequestToEndpoint(String requestType, String endpoint, List<Car> carList) {
//        switch (requestType) {
//            case "GET": response = request.get(endpoint);
//                break;
//            case "POST": response = request.post(endpoint, carEntry
//                break;
//            default:
//                throw new RuntimeException(requestType + " is not a valid request");
//        }
//    }

@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD) //Until this line was added the test would fail because it wasn't clearing the database after each run. HOWEVER running the get request on postman is returning multiple values
    @Given("the client sends a {string} request to {string} endpoint with the following:")
    public void theClientSendsARequestToEndpointWithTheFollowing(String requestType, String endpoint, DataTable dataTable) {
        List<Map<String, String>> dataTableList = dataTable.asMaps(String.class, String.class); // will be a list of the 6 values of car model

        // for (Map<String, String> tableMap : dataTableList) {
//            Map<String, String> replacedMap = new HashMap<>();
//            tableMap.forEach((key, value) -> {
//                replacedMap.put(key, value.equals("") ? "" : value);
//            });
//
//        }

        switch (requestType) {
            case "GET": response = request.get(endpoint);
                break;
            case "POST":
                RequestSpecification requestSpec = RestAssured.given().contentType(ContentType.JSON).body(dataTableList);
                response = requestSpec.post(endpoint);
                break;

            default:
                throw new RuntimeException(requestType + " is not a valid request");
        }
    }

    @Given("The database is empty")
    public void deleteCars() {
        request.delete("/cars/admin/delete");
    }
}

