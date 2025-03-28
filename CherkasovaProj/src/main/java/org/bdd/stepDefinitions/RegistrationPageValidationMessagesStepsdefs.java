package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class RegistrationPageValidationMessagesStepsdefs extends MainSteps {
    public RegistrationPageValidationMessagesStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Given("I open Login page page")
    public void iOpenLoginPagePage() {
        pageProvider.getLoginPage().openPage();
    }

    @When("I enter {string} into input Username in Login page")
    public void iEnterIntoInputUsernameInLoginPage(String userName) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName);
    }

    @And("I enter {string} into input Email in Login page")
    public void iEnterIntoInputEmailInLoginPage(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input Password in Login page")
    public void iEnterIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see alert message with text {string} in Registration page")
    public void iSeeAlertMessageWithTextInRegistrationPage(String errorMessage) {
        pageProvider.getLoginPage().checkErrorsMessages(errorMessage);
    }
}
