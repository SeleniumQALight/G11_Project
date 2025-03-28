package org.bdd.stepDefenitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStepdefs extends MainSteps {
    public HomePageStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
       pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileVisible();
       ;
    }
}
