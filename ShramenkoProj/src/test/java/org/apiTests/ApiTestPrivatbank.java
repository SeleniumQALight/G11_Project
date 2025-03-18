package org.apiTests;

import io.restassured.http.ContentType;
import org.api.EndPointsForPB;
import org.api.ExpectedResponse;
import org.api.dto.responseDTO.ExchangePB_DTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTestPrivatbank {
final String dateForExchangeRate = "22.03.2022";

    @Test
    public void getExchangeRange(){

        ExchangePB_DTO actualResponse =
        given()
                    .contentType(ContentType.JSON) //підготовка, header
                     .log().all()
                .when() //дія
                     .get(EndPointsForPB.EXCHANGE_RATES_URL,dateForExchangeRate)
                .then() //перевірка
                    .log().all()
                    .statusCode(200)
                .body("date", equalTo(dateForExchangeRate))
                .body("bank", equalTo("PB"))
                .body("baseCurrency", equalTo(980))
                .body("baseCurrencyLit", equalTo("UAH"))
                .body("exchangeRate.baseCurrency", everyItem(equalTo("UAH")))
                .extract().body().as(ExchangePB_DTO.class) //розбери отриманий боді по прикладу, який описаний в класі ExchangePB_DTO
                ;


        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse.getExchangeRate())
                .usingRecursiveComparison()
                .ignoringFields("baseCurrency", "saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                .isEqualTo(ExpectedResponse.expectedResponse().getExchangeRate());

        softAssertions.assertAll();



    }
}
