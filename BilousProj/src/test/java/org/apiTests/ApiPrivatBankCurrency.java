package org.apiTests;

import io.restassured.http.ContentType;
import org.api.EndPoints;
import org.api.dataTransferObject.responseDTO.CurrencyDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPrivatBankCurrency {

    @Test
    public void getCurrency() {
        CurrencyDTO[] actualResponse =
        given()
                    .contentType(ContentType.JSON) //precondition
                .when()
                    .get(EndPoints.BASE_URL_CURRENCY) //action URL
                .then()
                    .log().all()
                    .statusCode(200)
                .extract().body().as(CurrencyDTO[].class);

        CurrencyDTO[] expectedResponse = {
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("USD", "UAH")
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                        .usingRecursiveComparison()
                            .ignoringFields("buy", "sale")// як вказано в Java
                            .isEqualTo(expectedResponse);
        softAssertions.assertAll();
    }
}
