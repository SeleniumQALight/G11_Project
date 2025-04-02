package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.api.ApiHelperPrivatBank;
import org.api.dto.responseDTO.PrivatBankCurrencyRatesDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;
import org.pages.PrivatBankMainPage;

public class PrivatBankPageStepsdefs extends MainSteps {
    private ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();
    private PrivatBankMainPage privatBankMainPage;

    public PrivatBankPageStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        privatBankMainPage = new PrivatBankMainPage(webDriverHelper.getWebDriver());
    }

    @Given("I get currency rates from API for {string}")
    public void iGetCurrencyRatesFromAPIFor(String currency) {
        PrivatBankCurrencyRatesDTO[] rates = apiHelperPrivatBank.getCurrencyRates("5");
        for (PrivatBankCurrencyRatesDTO rate : rates) {
            if (rate.getCcy().equalsIgnoreCase(currency)) {
                TestDataPrivatBank.setBuyCurrencyRateApi(Double.parseDouble(rate.getBuy()));
                TestDataPrivatBank.setSaleCurrencyRateApi(Double.parseDouble(rate.getSale()));
                break;
            }
        }
    }

    @When("I open PrivatBank main page")
    public void iOpenPrivatBankMainPage() {
        privatBankMainPage.openPage().clickOnButtonExchangeRate();
    }

    @Then("I get currency rates from UI for {string}")
    public void iGetCurrencyRatesFromUIFor(String currency) {
        TestDataPrivatBank.setBuyCurrencyRateUi(privatBankMainPage.getBuyCurrencyRateUI(currency));
        TestDataPrivatBank.setSaleCurrencyRateUi(privatBankMainPage.getSaleCurrencyRateUI(currency));
    }

    @Then("I compare API and UI currency rates for {string}")
    public void iCompareApiAndUiCurrencyRatesFor(String currency) {
        assert TestDataPrivatBank.getBuyCurrencyRateApi().equals(TestDataPrivatBank.getBuyCurrencyRateUi()) : "Buy rates do not match for " + currency;
        assert TestDataPrivatBank.getSaleCurrencyRateApi().equals(TestDataPrivatBank.getSaleCurrencyRateUi()) : "Sale rates do not match for " + currency;
    }

}