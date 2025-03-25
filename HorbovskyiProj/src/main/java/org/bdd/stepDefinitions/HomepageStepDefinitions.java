package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomepageStepDefinitions extends MainSteps{

    public HomepageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar")
    public void iSeeAvatar() {
        pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible();
    }
}
