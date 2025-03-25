package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageMyStepdefs extends MainSteps {
    public HomePageMyStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSingOutVisible();
    }
}
