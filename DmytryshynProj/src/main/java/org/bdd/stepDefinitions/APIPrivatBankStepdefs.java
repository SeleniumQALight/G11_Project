package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelperPrivatBank;
import org.api.dto.responseDTO.PubInfoPrivatBankDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPrivatBank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class APIPrivatBankStepdefs extends MainSteps {
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public APIPrivatBankStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get exchange rate via Privat Bank API for {string}")
    public void iGetExchangeRateViaPrivatBankAPIFor(String nameOfExchange) {
        PubInfoPrivatBankDTO[] responsePubInfo = apiHelperPrivatBank.getExchangeRatePB("5");
        BigDecimal rateApiBuyDecimal;
        for (int i = 0; i < responsePubInfo.length; i++) {
            if (responsePubInfo[i].getCcy().equals(nameOfExchange)) {
                rateApiBuyDecimal = new BigDecimal(responsePubInfo[i].getBuy());
                TestDataPrivatBank.setRateApiBuy(rateApiBuyDecimal.setScale(
                        5, RoundingMode.HALF_UP).doubleValue());
            }
        }

        BigDecimal rateApiSellDecimal;
        for (int i = 0; i < responsePubInfo.length; i++) {
            if (responsePubInfo[i].getCcy().equals(nameOfExchange)) {
                rateApiSellDecimal = new BigDecimal(responsePubInfo[i].getSale());
                TestDataPrivatBank.setRateApiSell(rateApiSellDecimal.setScale(
                        5, RoundingMode.HALF_UP).doubleValue());
            }
        }

    }
}
