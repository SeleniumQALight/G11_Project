package org.bdd.stepDefenitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

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
}
