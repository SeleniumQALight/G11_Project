package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class RegistrationFormStepdefs extends MainSteps{
    public RegistrationFormStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I enter {string} in Username field on Login page")
    public void i_enter_in_username_field_on_login_page(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }
    @When("I enter {string} in Email field on Login page")
    public void i_enter_in_email_field_on_login_page(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);

    }
    @When("I enter {string} in Password field on Login page")
    public void i_enter_in_password_field_on_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);

    }
    @Then("I see {string} on Login page")
    public void i_see_on_login_page(String string) {
        pageProvider.getLoginPage().checkErrorsMessages(string);
    }
}
