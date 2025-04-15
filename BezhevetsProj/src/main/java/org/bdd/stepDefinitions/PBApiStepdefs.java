package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.api.ApiHelperPB;
import org.api.dto.responseDTO.PBCurrencyRatesDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.PBTestData;
import org.junit.Assert;

public class PBApiStepdefs extends MainSteps {

    private  ApiHelperPB apiHelperPB = new ApiHelperPB();

    public PBApiStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I have the {string} currency rate from API")
    public void retrieveCurrencyRateFromApi(String currency) {
        PBCurrencyRatesDTO[] currencyRatesDTO = apiHelperPB.getCurrencyRates("5");
        for (PBCurrencyRatesDTO rate : currencyRatesDTO) {
            if (rate.getCcy().equalsIgnoreCase(currency)) {
                PBTestData.BUY_CURRENCY_RATE_FROM_API = parseRateFromString(rate.getBuy());
                PBTestData.SALE_CURRENCY_RATE_FROM_API = parseRateFromString(rate.getSale());
                return;
            }
        }
        throw new IllegalStateException("Currency " + currency + " not found in API response");
    }

    @And("I compare the {string} currency rates from API and UI")
    public void iCompareTheCurrencyRatesFromAPIAndUI(String currency) {
        Assert.assertEquals("Buy rate from API and UI do not match for " + currency,
                PBTestData.BUY_CURRENCY_RATE_FROM_API, PBTestData.BUY_CURRENCY_RATE_FROM_UI);
        Assert.assertEquals("Sell rate from API and UI do not match for " + currency,
                PBTestData.SALE_CURRENCY_RATE_FROM_API, PBTestData.SALE_CURRENCY_RATE_FROM_UI);
    }

    private double parseRateFromString(String rate) {
        return Double.parseDouble(rate.trim());
    }
}
