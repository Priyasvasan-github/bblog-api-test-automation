package com.backbase.apis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.json.model.user.User;
import helper.GetTestProperties;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.given;

public class UserAPI extends BasePage {

    /**
     * Get current user using access token
     * @return User
     */
    @Step
    public User getCurrentUser(){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().get("/api/user");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetCurrentUserResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("GetCurrentUserResponse").to(rawResponse);
        return getUsersAsList(rawResponse.toString());
    }

    /**
     * Update a user using access token
     */
    @Step
    public void updateUser(){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization",Serenity.sessionVariableCalled("JWT_TOKEN"))
                .when().log().all().get("/api/user");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetCurrentUserResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("GetCurrentUserResponse").to(rawResponse);

    }

    /**
     * Contract validation for user response
     * @param response the API response
     * @return Validation
     */

    private User getUsersAsList(String response){
        try {
            return mapper.readValue(response, new TypeReference<User>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Users are not as per the Contract" + e.getMessage());
        }
    }

}
