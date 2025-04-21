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

    @And("I get {string} from UI")
    public void iGetCurrencyListFromUI(String currency) {
        pageProvider.getPrivatBankPage().openExchangeRates().getCurrencyRates(currency);
    }

    @Then("I compare currency between API and UI")
    public void iCompareCurrencyListBetweenAPIAndUI() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.CURRENCY_BUY_API).isEqualTo(TestData.CURRENCY_BUY_UI);
        softAssertions.assertThat(TestData.CURRENCY_SELL_API).isEqualTo(TestData.CURRENCY_SELL_UI);
        softAssertions.assertAll();
    }
}
