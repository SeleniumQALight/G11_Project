package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;
import org.junit.Assert;

public class PrivatBankComparisonStepsdefs extends MainSteps {

    public PrivatBankComparisonStepsdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Then("I compare API and UI currency rates for {string}")
    public void iCompareApiAndUiCurrencyRatesFor(String currency) {
        Assert.assertEquals("Buy rates do not match for " + currency,
                TestDataPrivatBank.BUY_CURRENCY_RATE_API,
                TestDataPrivatBank.BUY_CURRENCY_RATE_UI);

        Assert.assertEquals("Sale rates do not match for " + currency,
                TestDataPrivatBank.SALE_CURRENCY_RATE_API,
                TestDataPrivatBank.SALE_CURRENCY_RATE_UI);
    }
}

