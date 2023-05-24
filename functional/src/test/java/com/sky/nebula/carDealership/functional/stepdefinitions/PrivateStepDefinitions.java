package com.sky.nebula.carDealership.functional.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;

public class PrivateStepDefinitions {

    Response response;

    String requestData;

    String json;

    @When("client sends a {string} request to {string} endpoint")
    public void clientSendsARequestToEndpoint(String requestType, String endpoint) {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON);
        switch (requestType) {
            case "GET": response = request.get(endpoint);
            break;
            case "POST": response = request.body(requestData).post(endpoint);
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

}
