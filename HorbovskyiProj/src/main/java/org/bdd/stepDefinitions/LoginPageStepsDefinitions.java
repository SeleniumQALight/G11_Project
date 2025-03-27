package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageStepsDefinitions extends MainSteps {

    public LoginPageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage() {
        pageProvider.getLoginPage().openPage();

    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();

    }


    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String userName) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
    }

    @And("I enter {string} into input PassWord in Login page")
    public void iEnterIntoInputPassWordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String errorMessage) {
        //
        pageProvider.getLoginPage().checkTextInAlertInCenter(errorMessage);
    }
}
