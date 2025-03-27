package org.bdd.stepDefenitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

import static org.bdd.stepDefenitions.MainSteps.*;
import static org.data.TestData.*;

public class LoginPageStepsDefinitions extends MainSteps {
    public LoginPageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open login page")
    public void iOpenLoginPage(){
        pageProvider.getLoginPage().openPage();
    }


    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();

    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String username) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(username);
    }

    @And("I enter {string} into input PassWord in Login page")
    public void iEnterPasswordIntoInputPassWordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String alertMessage) {
        pageProvider.getLoginPage().checkErrorMessageText(alertMessage);
    }

    @And("I open Home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = VALID_PASSWORD_API;
        }
        pageProvider.getLoginPage().openPage().enterTextIntoInputLogin(userName).enterTextIntoInputPassword(password).clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectToHomePage();
    }
}
