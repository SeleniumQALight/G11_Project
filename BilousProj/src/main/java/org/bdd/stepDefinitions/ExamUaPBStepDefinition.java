package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class ExamUaPBStepDefinition extends MainSteps{

    public ExamUaPBStepDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I open the PrivatBank main page")
    public void iOpenThePrivatBankMainPage() {
      pageProvider.getExamPbMainPage().openMainPagePB();
      pageProvider.getExamPbMainPage().clickExchangeRatesButton();
    }

    @Then("I fetch the {string} exchange rate from PrivatBank UI")
    public void iFetchTheExchangeRateFromPrivatBankUI(String nameCurrency) {
        pageProvider.getExamPbMainPage().getExchangeRatesBuy(nameCurrency);
    }
}
