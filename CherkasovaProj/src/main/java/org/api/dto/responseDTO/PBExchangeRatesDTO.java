package org.api.dto.responseDTO;

import java.util.List;

public class PBExchangeRatesDTO {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRateDTO> exchangeRate;


    public PBExchangeRatesDTO() {

    }

    public PBExchangeRatesDTO(String date, String bank, Integer baseCurrency, String baseCurrencyLit, List<ExchangeRateDTO> exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public List<ExchangeRateDTO> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<ExchangeRateDTO> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "PBExchangeRatesDTO{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
