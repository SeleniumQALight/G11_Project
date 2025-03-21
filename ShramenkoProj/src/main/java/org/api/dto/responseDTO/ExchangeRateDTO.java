package org.api.dto.responseDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class ExchangeRateDTO {

    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public ExchangeRateDTO() {
    }

    public ExchangeRateDTO(String baseCurrency, String currency, Double saleRateNB, Double purchaseRateNB, Double saleRate, Double purchaseRate) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public ExchangeRateDTO(String currency) {
        this.currency = currency;
    }

    public ExchangeRateDTO(String baseCurrency, String currency, Double saleRateNB, Double purchaseRateNB) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
    }
}