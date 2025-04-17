package org.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.PrivatBankDTO.ExamCurrencyDTO;
import org.data.TestData;

import static io.restassured.RestAssured.given;

public class ApiHelperExamPB {
    Logger logger = Logger.getLogger(getClass());
    @Getter
    public String currencyBuyResponseApi;
    @Getter
    public String currencySaleResponseApi;

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .addFilter(new AllureRestAssured())
            .build();
    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK) // 200
            .build();

    public void getExchangeRateForCurrency(String nameCurrency) {
        ExamCurrencyDTO[] responseApi =
                given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPB.EXCHANGE_RATES_EUR_USD)
                .then()
                .spec(responseSpecification)
                .extract().body().as(ExamCurrencyDTO[].class);
        if (nameCurrency.equals("EUR")) {
            int i = 0;
            TestData.currencyBuyApi = Math.round(Double.parseDouble(responseApi[i].getBuy())*10)/10;
            System.out.println("BuyAPI: " + TestData.currencyBuyApi);
            TestData.currencySaleApi = Math.round(Double.parseDouble(responseApi[i].getSale())*10)/10;
            System.out.println("SaleAPI: " + TestData.currencySaleApi);
        } else if (nameCurrency.equals("USD")) {
            int i = 1;
            TestData.currencyBuyApi = Math.round(Double.parseDouble(responseApi[i].getBuy())*10)/10;
            System.out.println("BuyAPI: " + TestData.currencyBuyApi);
            TestData.currencySaleApi = Math.round(Double.parseDouble(responseApi[i].getSale())*10)/10;
            System.out.println("SaleAPI: " + TestData.currencySaleApi);
        }
    }
}
