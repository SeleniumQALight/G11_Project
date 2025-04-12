package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.pages.PrivatBankHomePage;

public class PrivatBankExchangeRatesUiStepdef extends MainSteps {
    private PrivatBankHomePage privatBankHomePage;

    public PrivatBankExchangeRatesUiStepdef(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        this.privatBankHomePage = new PrivatBankHomePage(webDriverHelper.getWebDriver());
    }

    @Given("I open main page of Privat bank")
    public void i_open_main_page_of_privat_bank() {
        privatBankHomePage.openPage();
    }

    @When("I get {string} from UI")
    public void i_get_from_ui(String exchangeRates) {
        privatBankHomePage.getExchangeRatesFromUi(exchangeRates);
    }

    @Then("Check that exchange rates are the same on UI as on API")
    public void check_that_exchange_rates_are_the_same_on_ui_as_on_api() {
        privatBankHomePage.checkExchangeRates();
    }
}
