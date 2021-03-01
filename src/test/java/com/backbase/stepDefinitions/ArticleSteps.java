package com.backbase.stepDefinitions;

import com.backbase.apis.ArticlesAPI;
import com.json.model.articles.Article__1;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.GetTestProperties;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleSteps {

    @Steps
    ArticlesAPI articlesAPI;

    @When("^the user creates an article with valid details (.*),(.*),(.*),(.*)$")
    public void createArticle(String title, String description,String body,String tags) {
        articlesAPI.createArticle(title,description,body,tags);
    }


    @Then("^Article (.*) created successfully$")
    public void verifyArticleCreation(String status) {
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("CreateArticleResponseCode").toString()).isEqualTo("200");
            assertThat(Serenity.sessionVariableCalled("slugForSession").equals(null)).isFalse();
        }else{
            assertThat(Serenity.sessionVariableCalled("CreateArticleUserResponse").toString().equalsIgnoreCase("200")).isFalse();
            assertThat(Serenity.sessionVariableCalled("slugForSession").equals(null)).isTrue();

        }
    }

    @Then("^all the article details are displayed as expected (.*),(.*),(.*),(.*)$")
    public void viewArticle(String title, String description,String body,String tags) {
        Article__1 articleRetrieved = articlesAPI.getArticleWithSlug(GetTestProperties.getValue("latestSlug")).getArticle();
        assertThat(articleRetrieved.getTitle()).isEqualToIgnoringCase(title);
        assertThat(articleRetrieved.getDescription()).isEqualToIgnoringCase(description);
        assertThat(articleRetrieved.getBody()).isEqualToIgnoringCase(body);
        String[] tagsArray = tags.split("-");
        assertThat(articleRetrieved.getTagList().toArray()).isEqualTo(tagsArray);
        assertThat(articleRetrieved.getAuthor().getUsername()).isEqualToIgnoringCase(Serenity.sessionVariableCalled("username"));
    }

    @Then("^article value for (.*) is updated with (.*)$")
    public void verifyUpdate(String updateField,String updateValue) {
        Article__1 articleRetrieved = articlesAPI.getArticleWithSlug(GetTestProperties.getValue("latestSlug")).getArticle();
        switch (updateField.toUpperCase()){
            case "TITLE":
                assertThat(articleRetrieved.getTitle()).isEqualToIgnoringCase(updateValue);
                break;
            case "BODY":
                assertThat(articleRetrieved.getBody()).isEqualToIgnoringCase(updateValue);
                break;
            case "DESCRIPTION":
                assertThat(articleRetrieved.getDescription()).isEqualToIgnoringCase(updateValue);
                break;
        }
        assertThat(articleRetrieved.getAuthor().getUsername()).isEqualToIgnoringCase(Serenity.sessionVariableCalled("username"));
    }

    @When("^the user updates the article with (.*) to new value (.*)$")
    public void updateArticle(String updateField,String updateValue) {
        articlesAPI.updateArticle(updateField,updateValue, GetTestProperties.getValue("latestSlug"));
    }

    @Then("^Article (.*) updated successfully$")
    public void verifyUpdateStatus(String status) {
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("UpdateArticleResponseCode").toString()).isEqualTo("200");
            assertThat(Serenity.sessionVariableCalled("updatedSlugForSession").equals(null)).isFalse();
        }else{
            assertThat(Serenity.sessionVariableCalled("UpdateArticleResponseCode").toString().equalsIgnoreCase("200")).isFalse();
            assertThat(Serenity.sessionVariableCalled("updatedSlugForSession").equals(null)).isTrue();
        }
    }


    @When("^He delete the existing Article$")
    public void deleteArticle() {
        articlesAPI.deleteArticle(GetTestProperties.getValue("latestSlug"));
    }


    @Then("^Article (.*) deleted successfully$")
    public void verifyDelete(String status) {
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("DeleteArticleResponseCode").toString()).isEqualTo("204");
        }else{
            assertThat(Serenity.sessionVariableCalled("DeleteArticleResponseCode").toString()).isEqualTo("404");
        }
    }

    @Then("^Article (.*) found for viewing$")
    public void verifyViewArticle(String status) {
        articlesAPI.getArticleWithSlug(GetTestProperties.getValue("latestSlug")).getArticle();
        if("is".equalsIgnoreCase(status)){
            assertThat(Serenity.sessionVariableCalled("GetArticleResponseCode").toString()).isEqualTo("200");
        }else{
            assertThat(Serenity.sessionVariableCalled("GetArticleResponseCode").toString()).isEqualTo("404");
        }
    }

}
