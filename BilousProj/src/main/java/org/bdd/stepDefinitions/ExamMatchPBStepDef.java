package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import org.data.TestData;
import org.junit.Assert;

public class ExamMatchPBStepDef {

    @And("I match the API with UI exchange rates for {string}")
    public void iMatchTheAPIWithUIExchangeRatesFor(String arg0) {
        Assert.assertEquals(TestData.currencyBuyApi, TestData.currencyBuyUi, 0.1);
        Assert.assertEquals(TestData.currencySaleApi, TestData.currencySaleUi, 0.1);
    }
}
