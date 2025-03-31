package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.RegistrationValidationMessages;
import org.data.TestData;

public class LoginPageStepsDefinitions extends MainSteps {
  public LoginPageStepsDefinitions(WebDriverHelper webDriverHelper) {
    super(webDriverHelper);
  }

  @Given("I open Login page")
  public void iOpenLoginPage() {
    pageProvider.getLoginPage().openPage();
  }

  @When("I login with valid cred")
  public void iLoginWithValidCred() {
    pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN);
    pageProvider.getLoginPage().enterTextIntoInputPassw0rd(TestData.VALID_PASSWORD);
    pageProvider.getLoginPage().clickOnButtonSignIn();
  }

  @When("I enter {string} into input Login in Login page")
  public void i_enter_into_input_login_in_login_page(String userName) {
    pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
  }

  @When("I enter {string} into input PassWord in Login page")
  public void i_enter_into_input_pass_word_in_login_page(String password) {
    pageProvider.getLoginPage().enterTextIntoInputPassw0rd(password);
  }

  @When("I click on button SignIn in Login page")
  public void i_click_on_button_sign_in_in_login_page() {
    pageProvider.getLoginPage().clickOnButtonSignIn();
  }

  @Then("I see alert message with text {string}")
  public void i_see_alert_message_with_text(String errorMessage) {
    pageProvider.getLoginPage().checkTextInAlertInCenter(errorMessage);
  }

  @Then("I see alert message with {string} text")
  public void iSeeAlertMessageWithErrorMessageText(String expectedMessage) {
    pageProvider.getLoginPage().checkErrorsMessages(expectedMessage);
  }

  @When("I enter {string} on sign up form input Username in Login page")
  public void iEnterOnSignUpFormInputUsernameInLoginPage(String username) {
    pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
  }

  @And("I enter {string} on sign up form input Email in Login page")
  public void iEnterOnSignUpFormInputEmailInLoginPage(String email) {
    pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
  }

  @And("I enter {string} on sign up form input Password in Login page")
  public void iEnterOnSignUpFormInputPasswordInLoginPage(String password) {
    pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
  }

  @And("I click on button SignUp in Login page")
  public void iClickOnButtonSignUpInLoginPage() {
    pageProvider.getLoginPage().clickOnButtonSignUp();
  }
}
