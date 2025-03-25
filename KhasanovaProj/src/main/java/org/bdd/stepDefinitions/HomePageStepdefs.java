package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStepdefs extends MainSteps {
    public HomePageStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on page")
    public void iSeeAvatarOnPage() {
        pageProvider.getHomePage().getHeaderForUserElement().checkIsButtonMyProfileVisible();
    }
}
