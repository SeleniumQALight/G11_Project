package org.bdd.stepDefenitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.api.ApiHelper;
import org.data.TestData;

public class ApiStepdefs {
    ApiHelper apiHelper = new ApiHelper();


    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNumberOfPostsNewPostsViaAPIForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String username, String password, DataTable dataTable) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(username, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());

    }


}

