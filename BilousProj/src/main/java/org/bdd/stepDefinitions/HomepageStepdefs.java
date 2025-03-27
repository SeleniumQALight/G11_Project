package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class HomepageStepdefs extends MainSteps {
    public HomepageStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }

    @And("I open Home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoPassword(password)
                .clickInButtonSignIn();
        
        pageProvider.getHomePage().checkIsRedirectOnHomePage();
        
    }


}
