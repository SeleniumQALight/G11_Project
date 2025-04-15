package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.PBTestData;
import org.pages.PBHomePage;

public class PBUIStepdefs extends MainSteps {
    public PBUIStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    PBHomePage PBHomePage = new PBHomePage(webDriverHelper.getWebDriver());

    @When("I open the PrivatBank home page")
    public void iOpenThePrivatBankHomePage() {
        PBHomePage.openPage().clickOnExchangeRateButton();
    }

    @Then("I have the {string} currency rate from UI")
    public void iHaveTheCurrencyRateFromUI(String currency) {
        PBTestData.BUY_CURRENCY_RATE_FROM_UI = PBHomePage.getBuyRate(currency);
        PBTestData.SALE_CURRENCY_RATE_FROM_UI = PBHomePage.getSellRate(currency);
    }
}
