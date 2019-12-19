package com.rest_api.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import static io.restassured.RestAssured.given;

public class HttpRequestConfiguration {
    // Set the User name for specific user details.
    public String setUserName = "Samanthaaaaa";

    // Set the RestAssured base uri for entire test. All the tests will fail in case of invalid url.
    @BeforeSuite(alwaysRun = true)
    public void config(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
    }

    //  Fetch the UserID for User {setUsername} from JSON Response.

    public String GetUserId() {
        HttpRequestConfiguration config = new HttpRequestConfiguration();
        Response response = config.GetRequest(EndPointConfig.GET_USERS,"username",setUserName);
        String userId=response.jsonPath().getString("id[0]");
        return userId;
    }

    // Rest Assured GET Request.
    public Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    // Rest Assured GET Request with Query parameters
    public Response GetRequest(String endpoint, String queryVariable, String queryValue) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().param(queryVariable,queryValue).when().get(endpoint).
                        then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

}
