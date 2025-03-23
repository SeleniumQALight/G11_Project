package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.dto.responseDTO.GetCurrencyInfoDTO;
import org.api.dto.responseDTO.GetCurrencyRateDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.PrivatBankEndPoints.GET_CURRENCY_RATE;

public class GetCurrencyRateTest {
    private Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getCurrencyRate(){
        GetCurrencyInfoDTO actualResponse =
                        given().contentType(ContentType.JSON).queryParam("date","22.03.2022").log().all().
                        when().get(GET_CURRENCY_RATE).
                        then().log().all().statusCode(200).extract().body().as(GetCurrencyInfoDTO.class);
        ;

        GetCurrencyInfoDTO expectedResponse = GetCurrencyInfoDTO.builder().
                date("22.03.2022").
                bank("PB").
                baseCurrency(980).
                baseCurrencyLit("UAH").
                exchangeRate(new GetCurrencyRateDTO[]{
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("UAH").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                        GetCurrencyRateDTO.builder().baseCurrency("UAH").currency("UZS").build(),

                }).build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse).usingRecursiveComparison().ignoringFields("exchangeRate").isEqualTo(expectedResponse);
        softAssertions.assertThat(actualResponse.getExchangeRate()).usingRecursiveComparison().ignoringFields("saleRateNB","purchaseRateNB","saleRate","purchaseRate").isEqualTo(expectedResponse.getExchangeRate());

        softAssertions.assertAll();





    }

    @Test
    public void validateEchangeRatesGreaterThanZeroTest(){
        GetCurrencyInfoDTO actualResponse =
                given().contentType(ContentType.JSON).queryParam("date","22.03.2022").log().all().
                        when().get(GET_CURRENCY_RATE).
                        then().log().all().statusCode(200).extract().body().as(GetCurrencyInfoDTO.class);
        ;

        SoftAssertions softAssertions = new SoftAssertions();
        for (GetCurrencyRateDTO exchangeRate: actualResponse.getExchangeRate()){
            softAssertions.assertThat(exchangeRate.getSaleRateNB()).as("Sale rate NB").isGreaterThan(0);
            softAssertions.assertThat(exchangeRate.getPurchaseRateNB()).as("Purchase rate NB").isGreaterThan(0);
            if (exchangeRate.getSaleRate() != 0){
                softAssertions.assertThat(exchangeRate.getSaleRate()).as("Sale rate").isGreaterThan(0);
            }
            if (exchangeRate.getPurchaseRate() != 0){
                softAssertions.assertThat(exchangeRate.getPurchaseRate()).as("Purchase rate").isGreaterThan(0);
            }
        }
        softAssertions.assertAll();
    }

}
