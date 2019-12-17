package com.rest_api.test;
import com.rest_api.framework.CommonParameterConfiguration;
import com.rest_api.framework.EndPointConfig;
import com.rest_api.framework.HttpRequestConfiguration;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Blogs {
    /*
    The objective of this method is to fetch all the posts for specific user i.e "HttpRequestConfiguration class
    setUsername variable" The test will fail if the user name doesn't exits. The method first check if the user name
    exists in JSON Response. Thereafter Fetch the posts by user.The Test will fail in case of invalid request url.
    Set the EndPoint GET_POSTS variable in EndPointConfig class for Post API Request.
   */
    @Test(priority = 3, groups = {"blog"})

    public void Search_Posts_By_User() {
        HttpRequestConfiguration config = new HttpRequestConfiguration();
        Response response = config.doGetRequest(EndPointConfig.GET_USERS);
        List<String> usernameList = response.jsonPath().getList("username");
        Assert.assertEquals(usernameList.contains(config.setUserName), true, config.setUserName+" User Doesnt's Exits");
        FetchPosts(config);
    }

    public void FetchPosts(HttpRequestConfiguration hrc){
        Response response = hrc.GetRequest(EndPointConfig.GET_POSTS,"userId",hrc.GetUserId());
        Assert.assertEquals(response.getStatusCode(), 200, " API_HOST Response Status Code Error..");
        List<String> postsList = response.jsonPath().getList("$");
        System.out.println("Total Number Of Posts By : - " + hrc.setUserName + " = " + postsList.size());
        System.out.println(postsList);
    }

}
