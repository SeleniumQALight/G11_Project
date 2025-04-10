package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPB;
import org.junit.Assert;

public class UiPbStepsDefinition extends MainSteps {
    Double rateUiBuy = TestDataPB.getRateUiBuy();
    Double rateApiBuy = TestDataPB.getRateApiBuy();
    Double rateUiSell = TestDataPB.getRateUiSell();
    Double rateApiSell = TestDataPB.getRateApiSell();

    Logger logger = Logger.getLogger(getClass());


    public UiPbStepsDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I get exchange rate via UI for {string}")
    public void iGetExchangeRateViaUI(String nameOfExchange) {
        pageProvider.getPbHomePage().openPage().clickOnButtonExchangeInHeader();
        rateUiBuy = pageProvider.getPbHomePage().getRateBuyFromUI(nameOfExchange);
        TestDataPB.setRateUiBuy(rateUiBuy);
        rateUiSell = pageProvider.getPbHomePage().getRateSellFromUI(nameOfExchange);
        TestDataPB.setRateUiSell(rateUiSell);
    }

    @Then("I compare the API and UI exchange rates for {string}")
    public void iCompareTheAPIAndUIExchangeRatesFor(String nameOfExchange) {
        logger.info("Buy - " + nameOfExchange + ": API " + rateApiBuy + ", UI = " + rateUiBuy);
        logger.info("Sell - " + nameOfExchange + ": API " + rateApiSell + ", UI = " + rateUiSell);
        Assert.assertEquals("Buy rate of exchange ", rateApiBuy, rateUiBuy);
        Assert.assertEquals("Sell rate of exchange ", rateApiSell, rateUiSell);
    }
}
