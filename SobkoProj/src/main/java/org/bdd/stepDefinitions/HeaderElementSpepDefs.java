package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.pages.PageProvider;

public class HeaderElementSpepDefs  extends MainSteps{
    public HeaderElementSpepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }



    @When("I click on button MyProfile on Header Element")
    public void iClickOnButtonMyProfileOnHeaderElement() {
        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile();

    }
}
