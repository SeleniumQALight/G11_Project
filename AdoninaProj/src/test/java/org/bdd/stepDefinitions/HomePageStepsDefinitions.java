package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStepsDefinitions extends MainSteps{
  public HomePageStepsDefinitions(WebDriverHelper webDriverHelper) {
    super(webDriverHelper);
  }

  @Then("I see avatar on HomePage")
  public void iSeeAvatarOnHomePage(){
    pageProvider.getHomePage().getHeaderElement().checkIsSearchButtonVisible();

  }
}
