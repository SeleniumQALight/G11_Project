package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class PrivatBankStepDefs extends MainSteps{

    public PrivatBankStepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @When("I open PrivatBank UI page")
    public void iOpenPrivatBankUIPage() {
        pageProvider.getPrivatBankPage().openPage();
    }

    @And("I get currency list from UI")
    public void iGetCurrencyListFromUI() {
        pageProvider.getPrivatBankPage().openExchangeRates().getCurrencyRates();
    }

    @Then("I compare currency list between API and UI")
    public void iCompareCurrencyListBetweenAPIAndUI() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.EUR_BUY_API).as("EUR_BUY_API").isEqualTo(TestData.EUR_BUY_UI);
        softAssertions.assertThat(TestData.EUR_SELL_API).as("EUR_SELL_API").isEqualTo(TestData.EUR_SELL_UI);
        softAssertions.assertThat(TestData.USD_BUY_API).as("USD_BUY_API").isEqualTo(TestData.USD_BUY_UI);
        softAssertions.assertThat(TestData.USD_SELL_API).as("USD_SELL_API").isEqualTo(TestData.USD_SELL_UI);
        softAssertions.assertThat(TestData.PLN_BUY_API).as("PLN_BUY_API").isEqualTo(TestData.PLN_BUY_UI);
        softAssertions.assertThat(TestData.PLN_SELL_API).as("PLN_SELL_API").isEqualTo(TestData.PLN_SELL_UI);

        softAssertions.assertAll();
    }
}
