package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelperPrivatBank;
import org.api.dto.responseDTO.PrivatBankCurrencyRatesDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;


public class ApiPrivatBankPageStepsdefs extends MainSteps {
    private ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public ApiPrivatBankPageStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get currency rates from API for {string}")
    public void iGetCurrencyRatesFromAPIFor(String currency) {
        PrivatBankCurrencyRatesDTO[] rates = apiHelperPrivatBank.getCurrencyRates("5");
        for (PrivatBankCurrencyRatesDTO rate : rates) {
            if (rate.getCcy().equalsIgnoreCase(currency)) {
                TestDataPrivatBank.BUY_CURRENCY_RATE_API = Double.parseDouble(rate.getBuy());
                TestDataPrivatBank.SALE_CURRENCY_RATE_API = Double.parseDouble(rate.getSale());
                break;
            }
        }
    }
}