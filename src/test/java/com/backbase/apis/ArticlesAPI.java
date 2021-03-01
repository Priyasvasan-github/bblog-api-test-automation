package com.backbase.apis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.json.model.articles.Article;
import helper.GetTestProperties;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.given;

public class ArticlesAPI extends BasePage {

    GetTestProperties testProperties =new GetTestProperties();

    /**
     * Create article using below parameters
     * @param title The title of the Article
     * @param description The description of the Article
     * @param body The body of the Article
     * @param tags The tags of the Article
     * @return Article
     */
    @Step
    public Article createArticle(String title, String description, String body, String tags){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().body(createArticleMsg(title,description,body,tags)).post("/api/articles");
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        JSONObject articleObject = new JSONObject(rawResponse.get("article").toString());
        testProperties.setValue("latestSlug",articleObject.get("slug").toString());
        Serenity.setSessionVariable("slugForSession").to(articleObject.get("slug").toString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("CreateArticleResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("CreateArticleUserResponse").to(rawResponse);
        return getArticle(rawResponse.toString());
    }

    /**
     * Update Article using the slug
     * @param parameter The parameter to update
     * @param value the value of the parameter
     * @param slug The slug of the article
     * @return Article
     */
    @Step
    public Article updateArticle(String parameter, String value, String slug){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().body("{\"article\":{\""+parameter+"\":\""+value+"\"}}").put("/api/articles/"+slug);
        JSONObject rawResponse = new JSONObject(response.getBody().asString());
        JSONObject articleObject = new JSONObject(rawResponse.get("article").toString());
        testProperties.setValue("latestSlug",articleObject.get("slug").toString());
        Serenity.setSessionVariable("updatedSlugForSession").to(articleObject.get("slug").toString());
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("UpdateArticleResponseCode").to(responseStatusCode);
        Serenity.setSessionVariable("UpdateArticleUserResponse").to(rawResponse);
        return getArticle(rawResponse.toString());
    }

    /**
     * Delete article using slug
     * @param slug The slug of the article
     */
    @Step
    public void deleteArticle(String slug){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().delete("/api/articles/"+slug);
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("DeleteArticleResponseCode").to(responseStatusCode);
    }

    /**
     * Retrieve an article using the slug
     * @param slug The slug of the article
     * @return Article
     */
    @Step
    public Article getArticleWithSlug(String slug){
        Response response = given()
                .auth().basic(GetTestProperties.getValue("basicLoginUser"), GetTestProperties.getValue("basicLoginPassword"))
                .header("Content-Type","application/json")
                .header("Jwtauthorization","Bearer "+Serenity.sessionVariableCalled("GetUserToken"))
                .when().log().all().get("/api/articles/"+slug);
        int responseStatusCode=response.getStatusCode();
        Serenity.setSessionVariable("GetArticleResponseCode").to(responseStatusCode);
        if(responseStatusCode==200){
            JSONObject rawResponse = new JSONObject(response.getBody().asString());
            Serenity.setSessionVariable("GetArticleUserResponse").to(rawResponse);
            return getArticle(rawResponse.toString());
        }else{
            return new Article();
        }
    }

    /**
     * Contract validation of a Article response
     * @param response the API response
     * @return Validation
     */
    private Article getArticle(String response){
        try {
            return mapper.readValue(response, new TypeReference<Article>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Article is not as per the Contract" + e.getMessage());
        }
    }

    /**
     * Request body to create a Article
     * @param title The title of the Article
     * @param description The description of the Article
     * @param body The body of the Article
     * @param tags The tags of the Article
     * @return Article
     */
    public String createArticleMsg(String title, String description,String body,String tags){
        JSONObject json = new JSONObject();

        JSONObject articleDetails = new JSONObject();
        articleDetails.put("title", title);
        articleDetails.put("description", description);
        articleDetails.put("body", body);
        String[] tagsArray = tags.split("-");
        articleDetails.put("tagList", tagsArray);
        json.put("article", articleDetails);

        return json.toString();
    }

}
