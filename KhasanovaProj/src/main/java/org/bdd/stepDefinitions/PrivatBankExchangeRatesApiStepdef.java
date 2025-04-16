package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.api.ApiHelperForPrivatBank;
import org.bdd.helpers.WebDriverHelper;
import org.pages.PrivatBankHomePage;

public class PrivatBankExchangeRatesApiStepdef extends MainSteps {
    private PrivatBankHomePage privatBankHomePage;

    public PrivatBankExchangeRatesApiStepdef(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        this.privatBankHomePage = new PrivatBankHomePage(webDriverHelper.getWebDriver());
    }

    @Given("I get {string} from Privat bank")
    public void i_get_from_privat_bank(String exchangeRates) {
        ApiHelperForPrivatBank.getExchangeRates(exchangeRates);
    }

    @When("I click on button Exchange rates on Header Element")
    public void i_click_on_button_exchange_rates_on_header_element() {
        privatBankHomePage.clickOnExchangeRateButton();
    }

}
