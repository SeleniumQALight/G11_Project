package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPivatBank;
import org.junit.Assert;

public class PrivatBankCompareAPIandUIStepsdefs extends MainSteps {

    public PrivatBankCompareAPIandUIStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Then("I compare API exchange rate and UI exchange rate for {string}")
    public void iCompareApiAndUiExchangeRatesFor(String currency) {
        Assert.assertEquals("Buy rates for " + currency,
                TestDataPivatBank.BUY_CURRENCY_RATE_API,
                TestDataPivatBank.BUY_CURRENCY_RATE_UI);

        Assert.assertEquals("Sale for " + currency,
                TestDataPivatBank.SALE_CURRENCY_RATE_API,
                TestDataPivatBank.SALE_CURRENCY_RATE_UI);
    }
}
