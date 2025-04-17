package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelperExamPB;

public class ExamApiPBStepDef {

    @Given("I fetch the {string} exchange rate from PrivatBank API")
    public void iFetchTheExchangeRateFromPrivatBankAPI(String nameCurrency) {
        ApiHelperExamPB apiHelperExamPB = new ApiHelperExamPB();
        apiHelperExamPB.getExchangeRateForCurrency(nameCurrency);
        System.out.println("Exchange rate for " + nameCurrency + " fetched successfully.");
    }
}
