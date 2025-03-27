package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

import static org.bdd.stepDefinitions.MainSteps.*;

public class HomePageStepsDefinitions extends MainSteps{
    public HomePageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderElement().checkIsAvatarVisible();
    }

    @And("I open Home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        //обробимо спочатку дефолтні значення
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.getLoginPage()
                .openPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSighIn()
                .checkIsRedirectToHomePage();
    }




}
