package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageStepDefinitions extends MainSteps{
    public LoginPageStepDefinitions(WebDriverHelper webDriverHelper) {
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
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }
}
