package com.backbase.apis;


import helper.GetTestProperties;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static net.serenitybdd.rest.SerenityRest.given;

public class AuthenticationAPI extends BasePage {

    /**
     * Login using required credentials and stored the Required token as a session variable
     */
    @Step
    public void login(){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .when().log().all().body(userLoginMessage(Serenity.sessionVariableCalled("email").toString(),Serenity.sessionVariableCalled("password").toString())).post("/api/users/login");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetLoginStatusCode").to(responseStatusCode);
        if(responseStatusCode==200){
            String responseObject = rawResponse.get("user").toString();
            JSONObject userObject = new JSONObject(responseObject);
            String userToken = userObject.get("token").toString();
            Serenity.setSessionVariable("GetUserToken").to(userToken);
        }else{
            Serenity.setSessionVariable("GetLoginResponse").to(rawResponse);
        }
    }

    /**
     * Register a user using below parameters
     * @param email The user emailId
     * @param password The user password
     * @param username The user name
     */
    @Step
    public void registerAUser(String email,String password,String username){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .when().log().all().body(registerMessage(email,password,username)).post("/api/users/");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        System.out.println(rawResponse);
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetRegistrationStatusCode").to(responseStatusCode);
        if(responseStatusCode==200){
            String responseObject = rawResponse.get("user").toString();
            JSONObject userObject = new JSONObject(responseObject);
            String userToken = userObject.get("token").toString();
            System.out.println(userToken);
            Serenity.setSessionVariable("GetUserToken").to(userToken);
        }else{
            Serenity.setSessionVariable("GetRegistrationResponse").to(rawResponse);
        }

    }

    /**
     * Request body creation for login request using required parameters
     * @param email The user emailId
     * @param password The user password
     * @return Login
     */
    public String userLoginMessage(String email, String password){
        JSONObject json = new JSONObject();

        JSONObject loginDetails = new JSONObject();
            loginDetails.put("email", email);
            loginDetails.put("password", password);
        json.put("user", loginDetails);

        return json.toString();
    }

    /**
     * Request body creation for Register request using required parameters
     * @param email The user emailId
     * @param password The user password
     * @param username The user name
     * @return Register
     */
    public String registerMessage(String email, String password,String username){
        JSONObject json = new JSONObject();

        JSONObject registrationDetails = new JSONObject();
        if(username.equalsIgnoreCase("validName")){
            registrationDetails.put("email", getRegistrationDetails()+"@email.com");
            registrationDetails.put("username",getRegistrationDetails());
        }else{
            registrationDetails.put("email", email);
            registrationDetails.put("username",username);
        }
        registrationDetails.put("password", password);
        json.put("user", registrationDetails);


        return json.toString();
    }

    /**
     *Java method to create SHA-25 checksum for a data
     */
    private static String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in lower case.
     * @param hash Current dateTime
     * @return The hash of current dateTime
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }

    public String getRegistrationDetails(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hashData = getSHA256Hash(now.toString());
        return hashData;
    }

}
