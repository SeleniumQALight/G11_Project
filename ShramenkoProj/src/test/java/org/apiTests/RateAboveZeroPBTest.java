package org.apiTests;

import org.api.ApiHelperForPB;
import org.api.dto.responseDTO.ExchangePB_DTO;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class RateAboveZeroPBTest {
    final String date = "22.03.2022";
    ApiHelperForPB apiHelperForPB = new ApiHelperForPB();

    @Test
    public void testRateMoreThenZero() {

        ExchangePB_DTO actualResponse =
                apiHelperForPB.getExchangeRateForDatePB(date, 200)
                        .body("exchangeRate.saleRateNB.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.purchaseRateNB.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.saleRate.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .body("exchangeRate.purchaseRate.findAll { it != null }", everyItem(greaterThan(0.0f)))
                        .extract().body().as(ExchangePB_DTO.class);

    }
}
