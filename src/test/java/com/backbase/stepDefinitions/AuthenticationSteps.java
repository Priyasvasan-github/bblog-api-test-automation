package com.backbase.stepDefinitions;


import com.backbase.apis.*;
import com.json.model.profile.Profile__1;
import com.json.model.user.User__1;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import utils.resource.AccountDictionary;
import utils.resource.UserAccount;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationSteps {

    @Steps
    AuthenticationAPI authenticationAPI;

    @Steps
    UserAPI userAPI;

    @Steps
    ProfileAPI profileAPI;

    BasePage basePage = new BasePage();


    @Given("^A (.*) user is able to access the API$")
    public void accessPortal(String userType) {
        RestAssured.baseURI = basePage.getBaseURL();
        UserAccount accounts = AccountDictionary.getInstance().get(userType);
        Serenity.setSessionVariable("password").to(accounts.getCredentials().getPassword());
        Serenity.setSessionVariable("email").to(accounts.getCredentials().getEmail());
        Serenity.setSessionVariable("username").to(accounts.getCredentials().getUsername());
        Serenity.setSessionVariable("userType").to(userType);
    }

    @Given("^A (.*) user have a token to access API$")
    public void accessAPI(String userType) {
        RestAssured.baseURI = basePage.getBaseURL();
        UserAccount accounts = AccountDictionary.getInstance().get(userType);
        Serenity.setSessionVariable("password").to(accounts.getCredentials().getPassword());
        Serenity.setSessionVariable("email").to(accounts.getCredentials().getEmail());
        Serenity.setSessionVariable("userType").to(userType);
        Serenity.setSessionVariable("username").to(accounts.getCredentials().getUsername());
        authenticationAPI.login();
    }

    @When("^the user login using (.*) credentials$")
    public void login(String loginType) {
        authenticationAPI.login();
    }

    @When("^the (.*) user attempts to register$")
    public void register(String userType) {
        UserAccount accounts = AccountDictionary.getInstance().get(userType);
        authenticationAPI.registerAUser(accounts.getCredentials().getEmail(),accounts.getCredentials().getPassword(),accounts.getCredentials().getUsername());
    }

    @Then("^the user login (.*) successful$")
    public void verifyLogin(String status) {
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("GetLoginStatusCode").toString()).isEqualTo("200");
            assertThat(Serenity.sessionVariableCalled("GetUserToken").equals(null)).isFalse();
        }else{
            assertThat(Serenity.sessionVariableCalled("GetLoginStatusCode").toString()).isEqualTo("422");
            assertThat(Serenity.sessionVariableCalled("GetLoginResponse").toString().contains("error")).isTrue();
        }

    }

    @Then("^the user registration (.*) successful$")
    public void verifyRegistration(String status) {
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("GetRegistrationStatusCode").toString()).isEqualTo("200");
            assertThat(Serenity.sessionVariableCalled("GetUserToken").equals(null)).isFalse();
        }else{
            assertThat(Serenity.sessionVariableCalled("GetRegistrationStatusCode").toString()).isEqualTo("422");
            assertThat(Serenity.sessionVariableCalled("GetRegistrationResponse").toString().contains("error")).isTrue();
        }
    }

    @Then("^the (.*) user details are displayed correctly$")
    public void userDetailVerification(String userType) {
        UserAccount accounts = AccountDictionary.getInstance().get(userType);
        User__1 userDetails = userAPI.getCurrentUser().getUser();
        assertThat(userDetails.getUsername()).isEqualToIgnoringCase(accounts.getCredentials().getUsername());
        assertThat(userDetails.getEmail()).isEqualToIgnoringCase(accounts.getCredentials().getEmail());
    }

    @Then("^the user (.*) following user with username (.*)$")
    public void profileVerification(String status, String username) {
        Profile__1 profileDetails = profileAPI.getProfile(username.toLowerCase()).getProfile();
        assertThat(Serenity.sessionVariableCalled("GetProfileResponseCode").toString()).isEqualToIgnoringCase("200");
        if(status.equalsIgnoreCase("is")){
            assertThat(profileDetails.getFollowing()).isTrue();
        }else{
            assertThat(profileDetails.getFollowing()).isFalse();
        }
    }

    @Then("^the user decides to (.*) user with username (.*)$")
    public void followProfile(String status, String username) {
        if(status.equalsIgnoreCase("follow")){
            profileAPI.followProfile(username.toLowerCase());
            assertThat(Serenity.sessionVariableCalled("FollowProfileResponseCode").toString()).isEqualToIgnoringCase("200");
        }else{
            profileAPI.unFollowProfile(username.toLowerCase());
            assertThat(Serenity.sessionVariableCalled("UnFollowProfileResponseCode").toString()).isEqualToIgnoringCase("200");
        }

    }


}
