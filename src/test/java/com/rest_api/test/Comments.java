package com.rest_api.test;
import com.rest_api.framework.EndPointConfig;
import com.rest_api.framework.HttpRequestConfiguration;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.rest_api.framework.CommonParameterConfiguration.emailFormatRegEx;

public class Comments {

    /*
    The objective of this method is to fetch all the comments and validate email address format.
    Email address format validation regular expression is set in CommonParameterConfiguration emailFormatRegEx varibale.
    Thereafter Fetch the posts by user.The Test will fail in case of invalid request url.
    Set the EndPoint GET_COMMENTS variable in EndPointConfig class for Comments API Request.
   */

    @Test(priority = 4,groups = {"comment"})
    public void GetRequest_Fetch_Comments_Validate_Email_Format() {

        HttpRequestConfiguration config = new HttpRequestConfiguration();
        Response response = config.doGetRequest(EndPointConfig.GET_COMMENTS);
        Assert.assertEquals(response.getStatusCode(), 200, " API_HOST response status code error..");
        List<String> commentList = response.jsonPath().getList("email");
        System.out.println("Total Number of Comments : " + commentList.size());
        System.out.println("-------------------------------------------------");
        System.out.println("Comments Email address format validation starts using RegEx");
        int emailNumber = 0;
        for(String email : commentList)
        {
            Pattern pattern = Pattern.compile(emailFormatRegEx);
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
            if(matcher.matches() == false){
                emailNumber += emailNumber;
            }
        }
        System.out.println("Total Number of Emails = " + commentList.size() + " , Total Emails with wrong format = " +emailNumber );

    }
}
