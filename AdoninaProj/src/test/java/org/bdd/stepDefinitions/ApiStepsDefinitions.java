package org.bdd.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.api.ApiHelper;
import org.data.TestData;

public class ApiStepsDefinitions {
  ApiHelper apiHelper = new ApiHelper();

  @Given("I create {} new posts via API for {string} user and {string} password")

  public void iCreateNumberOfPostsNewPostsViaAPIForDefaultUserAndDefaultPassword(
          Integer numberOfPosts, String userName, String password, DataTable dataTable) {
    if (MainSteps.DEFAULT.equalsIgnoreCase(userName)){
      userName = TestData.VALID_LOGIN_API;
    }
    if(MainSteps.DEFAULT.equalsIgnoreCase(password)){
      password = TestData.VALID_PASSWORD_API;
    }
    String token = apiHelper.getToken(userName, password);
    apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());
  }

}
