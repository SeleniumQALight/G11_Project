package org.bdd.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import org.api.ApiHelper;
import org.api.dto.responseDTO.GetCurrencyInfoDTO;
import org.api.dto.responseDTO.GetCurrencyRateDTO;
import org.api.dto.responseDTO.GetExamCurrencyInfoDTO;
import org.api.dto.responseDTO.GetExamCurrencyRateDTO;
import org.data.TestData;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.api.PrivatBankEndPoints.GET_CURRENCY_INFO;
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


    @Given("I get {string} from API")
    public void iGetCurrencyListFromAPI(String currency) {

        GetExamCurrencyRateDTO[] response =
                given().contentType(ContentType.JSON).log().all().queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid", "5").
                when().get(GET_CURRENCY_INFO).
                then().log().all().statusCode(200).extract().body().as(GetExamCurrencyRateDTO[].class);

        for (GetExamCurrencyRateDTO rate : response) {
            if (rate.getCcy().equalsIgnoreCase(currency)) {
                TestData.CURRENCY_BUY_API = rate.getBuy();
                TestData.CURRENCY_SELL_API = rate.getSale();
                break;
            }
        }
    }


}

