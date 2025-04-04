package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import org.api.ApiHelperForPB;
import org.api.dto.responseDTO.PubInfoDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPB;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ApiPbStepsDefinition extends MainSteps{
    ApiHelperForPB apiHelperForPB = new ApiHelperForPB();

    public ApiPbStepsDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Given("I get exchange rate via API for {string}")
    public void iGetExchangeRateViaApi(String nameOfExchange) {
        PubInfoDTO[] responsePubInfo = apiHelperForPB.getExchangeRatePB("5");

        BigDecimal rateApiBuyDecimal;

        for (int i = 0; i < responsePubInfo.length; i++) {
            if (responsePubInfo[i].getCcy().equals(nameOfExchange)) {
                rateApiBuyDecimal = new BigDecimal(responsePubInfo[i].getBuy());
                TestDataPB.setRateApiBuy(rateApiBuyDecimal.setScale(
                        5, RoundingMode.HALF_UP).doubleValue());
            }
        }

        BigDecimal rateApiSellDecimal;

        for (int i = 0; i < responsePubInfo.length; i++) {
            if (responsePubInfo[i].getCcy().equals(nameOfExchange)) {
                rateApiSellDecimal = new BigDecimal(responsePubInfo[i].getSale());
                TestDataPB.setRateApiSell(rateApiSellDecimal.setScale(
                        5, RoundingMode.HALF_UP).doubleValue());
            }
        }

    }
}