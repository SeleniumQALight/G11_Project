package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import org.api.ApiHelperForPB;
import org.api.dto.responseDTO.PubInfoDTO;
import org.bdd.helpers.WebDriverHelper;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ApiPbStepsDefinition extends MainSteps{
    ApiHelperForPB apiHelperForPB = new ApiHelperForPB();
    String rateApi = "";
    String rateUI = "";

    public ApiPbStepsDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Given("I get exchange rate via API for {string}")
    public String iGetExchangeRateViaApi(String nameOfExchange) {
        PubInfoDTO[] responsePubInfo = apiHelperForPB.getExchangeRatePB();

        BigDecimal rateApiDecimal = BigDecimal.ZERO;

        if (nameOfExchange.equalsIgnoreCase("EUR")) {
            rateApiDecimal = new BigDecimal(responsePubInfo[0].getBuy());
        }
        if (nameOfExchange.equalsIgnoreCase("USD")) {
            rateApiDecimal = new BigDecimal(responsePubInfo[1].getBuy());
        }
        rateApi = rateApiDecimal.setScale(5, RoundingMode.HALF_UP).toString();
        return rateApi;
    }

    @When("I get exchange rate via UI for {string}")
    public String iGetExchangeRateViaUI(String nameOfExchange) {
        rateUI = pageProvider.getPbHomePage().openPage().getRateFromUI(nameOfExchange);
        return rateUI;
    }

    @Then("I compare the API and UI exchange rates for {string}")
    public void iCompareTheAPIAndUIExchangeRatesFor(String nameOfExchange) {
        Assert.assertEquals("Rate of exchange ", rateApi, rateUI);
    }
}
