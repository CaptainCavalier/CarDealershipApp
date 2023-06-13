package com.sky.nebula.carDealership.functional.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.nebula.carDealership.functional.config.datatabletype.Car;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PrivateStepDefinitions {

    Response response;

    String requestData;

    String json;

    private RequestSpecification request;

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




    @And("the response body should have the key {string} with the value of {string}")
    public void theResponseBodyShouldHaveTheKeyDatabaseWithTheValueOfUpdated() {
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
}
