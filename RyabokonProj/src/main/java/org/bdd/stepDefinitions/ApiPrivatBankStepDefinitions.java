package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelperPrivatBank;
import org.api.dto.responseDTO.PrivatBankCurrencyRatesDTO;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPivatBank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiPrivatBankStepDefinitions extends MainSteps {
    private static final Logger logger = LoggerFactory.getLogger(ApiPrivatBankStepDefinitions.class);
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public ApiPrivatBankStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the exchange rate for {string} from PrivatBank API")
    public void iGetTheExchangeRateForFromPrivatBankAPI(String currency) {
        PrivatBankCurrencyRatesDTO[] rates = apiHelperPrivatBank.getCurrencyRatesPrivatBank("5");
        for (PrivatBankCurrencyRatesDTO rate : rates) {
            if (rate.getCcy().equalsIgnoreCase(currency)) {
                TestDataPivatBank.BUY_CURRENCY_RATE_API = Double.parseDouble(rate.getBuy());
               TestDataPivatBank.SALE_CURRENCY_RATE_API = Double.parseDouble(rate.getSale());
                logger.info("Currency: {}", rate.getCcy());
                logger.info("Buy Rate: {}", rate.getBuy());
                logger.info("Sale Rate: {}", rate.getSale());
                break;
            }
        }

    }
}
