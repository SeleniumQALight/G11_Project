package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

import static org.bdd.stepDefinitions.MainSteps.DEFAULT;

public class HomePageMyStepdefs extends MainSteps {
    public HomePageMyStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSingOutVisible();
    }

    @And("I open Home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectToHomePage();
    }
}
