package org.api.dto.responseDTO;


public class ExchangeRateDTO {
  private String baseCurrency;
  private String currency;
  private Float saleRateNB;
  private Float purchaseRateNB;
  private Float saleRate;
  private Float purchaseRate;

  public ExchangeRateDTO(){

  }

  public ExchangeRateDTO(String baseCurrency, String currency) {
    this.baseCurrency = baseCurrency;
    this.currency = currency;
  }

  public String getBaseCurrency() {
    return baseCurrency;
  }

  public void setBaseCurrency(String baseCurrencyLit) {
    this.baseCurrency = baseCurrencyLit;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public double getSaleRateNB() {
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
    return "ExchangeRateDTO{" +
            "baseCurrencyLit='" + baseCurrency + '\'' +
            ", currency='" + currency + '\'' +
            '}';
  }
}
