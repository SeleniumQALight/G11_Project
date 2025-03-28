package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class RegistrationMessageDef extends MainSteps {
    public RegistrationMessageDef(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I input {string} into the username field")
    public void iInputIntoTheUsernameField(String login) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(login);
    }
    @And("I input {string} into the email field")
    public void iInputIntoTheEmailField(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }
    @And("I input {string} into the password field")
    public void iInputIntoThePasswordField(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }
    @Then("I should see the error messages {string}")
    public void iShouldSeeTheErrorMessages(String expectedErrorsMessages) {
        pageProvider.getLoginPage().checkErrorsMessages(expectedErrorsMessages);
    }
}
