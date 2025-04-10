package org.api;
import org.api.dto.responseDTO.ExchangeRatesInfoDTO;
import org.data.PrivatBankTestData;
import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;

public class ApiHelperForPrivatBank {

    public static void getExchangeRates(String currencyCode) {
        ExchangeRatesInfoDTO[] exchangeRates =
                given()
                        .spec(requestSpecification)
                        .param("json")
                        .param("exchange")
                        .queryParam("coursid", "5")
                        .when()
                        .get(PrivatBankEndPoints.GET_EXCHANGE_INFO)
                        .then()
                        .spec(responseSpecification)
                        .extract().as(ExchangeRatesInfoDTO[].class);

        for (ExchangeRatesInfoDTO rate : exchangeRates) {
            if (currencyCode.equals(rate.getCcy())) {
                PrivatBankTestData.setRatesApi(rate.getBuy(), rate.getSale());
                return;
            }
        }
    }
}
