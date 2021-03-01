package com.backbase.apis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.json.model.profile.Profile;
import helper.GetTestProperties;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.given;

public class ProfileAPI extends BasePage {

    /**
     * get a profile using userName
     * @param username The user name
     * @return Profile
     */
    @Step
    public Profile getProfile(String username){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().get("/api/profiles/"+username);
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetProfileResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("GetProfileResponse").to(rawResponse);
        return getProfileDetails(rawResponse.toString());
    }

    /**
     * Follow profile of another user
     * @param username The user name
     * @return Profile
     */
    @Step
    public Profile followProfile(String username){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().post("/api/profiles/"+username+"/follow");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("FollowProfileResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("FollowProfileResponse").to(rawResponse);
        return getProfileDetails(rawResponse.toString());
    }

    /**
     * Un-follow profile of following user
     * @param username The user name
     * @return Profile
     */
    @Step
    public Profile unFollowProfile(String username){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().delete("/api/profiles/"+username+"/follow");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("UnFollowProfileResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("UnFollowProfileResponse").to(rawResponse);
        return getProfileDetails(rawResponse.toString());
    }

    /**
     * Contract validation of a profile
     * @param response the API response
     * @return Profile
     */
    private Profile getProfileDetails(String response){
        try {
            return mapper.readValue(response, new TypeReference<Profile>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Profile is not as per the Contract" + e.getMessage());
        }
    }

}
