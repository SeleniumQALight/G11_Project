package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;
import org.pages.PrivatBankMainPage;

public class UiPrivatBankPageStepsdefs extends MainSteps {
    private PrivatBankMainPage privatBankMainPage;

    public UiPrivatBankPageStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        privatBankMainPage = new PrivatBankMainPage(webDriverHelper.getWebDriver());
    }

    @When("I open PrivatBank main page")
    public void iOpenPrivatBankMainPage() {
        privatBankMainPage.openPage().clickOnButtonExchangeRate();
    }

    @Then("I get currency rates from UI for {string}")
    public void iGetCurrencyRatesFromUIFor(String currency) {
        TestDataPrivatBank.BUY_CURRENCY_RATE_UI = privatBankMainPage.getBuyCurrencyRateUI(currency);
        TestDataPrivatBank.SALE_CURRENCY_RATE_UI = privatBankMainPage.getSaleCurrencyRateUI(currency);
    }


}
