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
    public void iOpenLoginPage(){
        pageProvider.getLoginPage().openPage();
    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSighIn();
    }
    @When("I enter {string} into input Login in Login page")
    public void i_enter_into_input_login_in_login_page(String userName) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
    }
    @When("I enter {string} into input PassWord in Login page")
    public void i_enter_into_input_pass_word_in_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }
    @When("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSighIn();
    }
    @Then("I see alert message with text {string}")
    public void i_see_alert_message_with_text(String expectedMessage) {
        pageProvider.getLoginPage().checkIsAlertIncorrectLoginPasswordVisible();
        pageProvider.getLoginPage().checkTextInAllertInCenter(expectedMessage);
    }


    @When("I enter {string} into input Username in Login page")
    public void iEnterIntoInputUsernameInLoginPage(String userName) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName);
    }

    @And("I enter {string} into input Email in Login page")
    public void iEnterIntoInputEmailInLoginPage(String email) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input CreatePassword in Login page")
    public void iEnterIntoInputCreatePasswordInLoginPage(String password) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see error message with text {string}")
    public void iSeeErrorMessageWithText(String expectedMessages) {
        pageProvider.getLoginPage()
                .checkErrorsMessages(expectedMessages);
    }
}
