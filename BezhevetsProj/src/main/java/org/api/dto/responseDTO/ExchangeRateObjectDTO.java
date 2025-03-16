package org.api.dto.responseDTO;

public class ExchangeRateObjectDTO {
    private String baseCurrency;
    private String currency;
    private Float saleRateNB;    // Ігноруємо в тестах
    private Float purchaseRateNB;// Ігноруємо в тестах
    private Float saleRate;      // Ігноруємо в тестах
    private Float purchaseRate;  // Ігноруємо в тестах

    public ExchangeRateObjectDTO() {}

    public ExchangeRateObjectDTO(String baseCurrency, String currency) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Float saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public Float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Float purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Float getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Float saleRate) {
        this.saleRate = saleRate;
    }

    public Float getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Float purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "ExchangeRateObjectDTO{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}