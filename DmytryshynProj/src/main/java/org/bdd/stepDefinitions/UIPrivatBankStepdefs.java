package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;
import org.junit.Assert;

public class UIPrivatBankStepdefs extends MainSteps {
    public UIPrivatBankStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    Double rateUiBuy = TestDataPrivatBank.getRateUiBuy();
    Double rateApiBuy = TestDataPrivatBank.getRateApiBuy();
    Double rateUiSell = TestDataPrivatBank.getRateUiSell();
    Double rateApiSell = TestDataPrivatBank.getRateApiSell();

    Logger logger = Logger.getLogger(getClass());

    @When("I get exchange rate via Privat Bank UI for {string}")
    public void iGetExchangeRateViaPrivatBankSiteFor(String nameOfExchange) {
        pageProvider.getPrivatBankHomePage().openPage()
                .clickExchangeRateButton();
        rateUiBuy = pageProvider.getPrivatBankHomePage().getBuyRateFromUI(nameOfExchange);
        TestDataPrivatBank.setRateUiBuy(rateUiBuy);
        rateUiSell = pageProvider.getPrivatBankHomePage().getSellRateFromUI(nameOfExchange);
        TestDataPrivatBank.setRateUiSell(rateUiSell);
    }

    @Then("I compare exchange rates on API and UI for {string}")
    public void iCompareExchangeRatesOnAPIAndUIFor(String nameOfExchange) {
        logger.info("Buy - " + nameOfExchange + ": API " + rateApiBuy + ", UI = " + rateUiBuy);
        logger.info("Sell - " + nameOfExchange + ": API " + rateApiSell + ", UI = " + rateUiSell);
        Assert.assertEquals("Buy rate of exchange ", rateApiBuy, rateUiBuy);
        Assert.assertEquals("Sell rate of exchange ", rateApiSell, rateUiSell);
    }

}
