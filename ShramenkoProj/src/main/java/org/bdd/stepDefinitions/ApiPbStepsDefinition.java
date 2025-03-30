package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelper;
import org.api.EndPointsForPB;
import org.api.dto.responseDTO.PubInfoDTO;

import static io.restassured.RestAssured.*;

public class ApiPbStepsDefinition {
    ApiHelper apiHelper = new ApiHelper();

    @Given("I get exchange rate via API")
    public void iGetExchangeRateViaApi() {
        String rateUsd;
        String rateEuro;

        PubInfoDTO responcePubInfo =
                given()
                        .spec(requestSpecification)
                        .queryParam("json", "")
                        .queryParam("exchange", "")
                        .queryParam("coursid", "5")
                        .when()
                        .get(EndPointsForPB.PUB_INFO)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().as(PubInfoDTO.class);
        rateUsd = responcePubInfo

    }
}
