package org.bdd.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import org.api.ApiHelper;
import org.api.dto.responseDTO.GetCurrencyInfoDTO;
import org.api.dto.responseDTO.GetCurrencyRateDTO;
import org.data.TestData;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.api.PrivatBankEndPoints.GET_CURRENCY_RATE;

public class ApiStepdefs {
    ApiHelper apiHelper = new ApiHelper();


    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNumberOfPostsNewPostsViaAPIForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String username, String password, DataTable dataTable) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(username, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());

    }


    @Given("I get currency list from API")
    public void iGetCurrencyListFromAPI() {
        String date = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );
        GetCurrencyInfoDTO response =
                given().contentType(ContentType.JSON).queryParam("date", date).log().all().
                when().get(GET_CURRENCY_RATE).
                then().log().all().statusCode(200).extract().body().as(GetCurrencyInfoDTO.class);

        for (GetCurrencyRateDTO currency : response.getExchangeRate()) {
            switch (currency.getCurrency()) {
                case "USD":
                    TestData.USD_BUY_API = currency.getPurchaseRate();
                    TestData.USD_SELL_API = currency.getSaleRate();
                    break;
                case "EUR":
                    TestData.EUR_BUY_API = currency.getPurchaseRate();
                    TestData.EUR_SELL_API = currency.getSaleRate();
                    break;
                case "PLN":
                    TestData.PLN_BUY_API = currency.getPurchaseRate();
                    TestData.PLN_SELL_API = currency.getSaleRate();
                    break;
            }
        }
    }
}

