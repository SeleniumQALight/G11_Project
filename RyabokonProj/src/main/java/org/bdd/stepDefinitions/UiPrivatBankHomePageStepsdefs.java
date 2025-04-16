package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPivatBank;
import org.pages.PrivatBankHomePage;

public class UiPrivatBankHomePageStepsdefs extends MainSteps {

    private PrivatBankHomePage privatBankHomePage;

    public UiPrivatBankHomePageStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        privatBankHomePage = new PrivatBankHomePage(webDriverHelper.getWebDriver());
    }



    @When("I open PrivatBank HomePage")
    public void iOpenPrivatBankHomePage() {
        privatBankHomePage.openPage().clickOnButtonExchangeRate();
    }

    @Then("I get the exchange rate for {string} from the UI")
    public void iGetTheExchangeRateForCurrencyFromTheUI(String currency) {
        TestDataPivatBank.BUY_CURRENCY_RATE_UI = privatBankHomePage.getBuyCurrencyExchangeRateUI(currency);
        TestDataPivatBank.SALE_CURRENCY_RATE_UI = privatBankHomePage.getSaleCurrencyExchangeRateUI(currency);
    }
}
