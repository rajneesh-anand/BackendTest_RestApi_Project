package com.rest_api.test;
import com.rest_api.framework.EndPointConfig;
import com.rest_api.framework.HttpRequestConfiguration;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Users {
    /*
    The objective of this method is to fetch all the users details. The Test will fail in case of invalid request url.
    Set the EndPoint GET_USERS variable in EndPointConfig class for Users API Request.
   */

    @Test(priority = 1, groups = {"user"})
    public void GetRequest_Fetch_Users() {
        HttpRequestConfiguration config = new HttpRequestConfiguration();
        Response response = config.doGetRequest(EndPointConfig.GET_USERS);
        Assert.assertEquals(response.getStatusCode(), 200, " API_HOST Response Status Code Error..");
        List<String> userList = response.jsonPath().getList("$");
        System.out.println("Total Number Of Users : " + userList.size());
        System.out.println("-------------------------------------------");
        System.out.println(userList);

    }

    /*
    The objective of this method is to find the specific user name set in "SetUserName_GetUserName" method above.
     Validates the request Url response."doGetRequest" method is HttpRequestConfiguration class return JSON response
     against the requested GET request. Set the EndPoint for API in EndPointConfig class.

   */

    @Test(priority = 2, groups = {"user"})
    public void Search_Specific_UserName() {
        HttpRequestConfiguration config = new HttpRequestConfiguration();
        Response response = config.doGetRequest(EndPointConfig.GET_USERS);
        List<String> usernameList = response.jsonPath().getList("username");
        String Blogger_UserName = config.setUserName;
        Assert.assertEquals(usernameList.contains(Blogger_UserName), true, Blogger_UserName+ "User Doesnt's Exits");
        System.out.println("Search for User Name : - " + Blogger_UserName);
        System.out.println("Record for -  " + Blogger_UserName + " - Found");
    }
}