package com.backbase.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.serenitybdd.core.pages.PageObject;
import utils.ForceStringDeserializer;

public class BasePage extends PageObject {

    /**
     * ObjectMapper to make contract validations
     */
    public final ObjectMapper mapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(String.class, new ForceStringDeserializer())
    );

    /**
     * Method to pick baseURL based on environment setup
     * @return
     */
    public String getBaseURL() {
        String env = System.getenv("ENV");
        if (env == null || env.isEmpty()) {
            return "https://qa-task.backbasecloud.com";
        } else {
            switch (env){
                case "localhost":
                    return "https://localhost:8080";
                case "test":
                    return "URL FOR TEST";
                case "test-staging":
                    return "URL FOR STAGING";
                case "prod-live":
                    return "https://qa-task.backbasecloud.com";
                case "mock":
                    return "URL FOR MOCK";
                default:
                    return "default URL";
            }
        }
    }

}
