package org.bdd.stepDefinitions;


import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class HeaderElementStepdefs extends MainSteps{
    public HeaderElementStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }



    @When("I click on button MyProfile on Header Element")
    public void iClickOnButtonMyProfileOnHeaderElement() {
        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile();
    }
}
