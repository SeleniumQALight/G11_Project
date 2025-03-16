package org.api.dto.responseDTO;

import java.util.Arrays;

public class PrivatBankExchangeRatesDTO {

    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private PrivatBankCurrenciesDTO[] exchangeRate;

    public PrivatBankExchangeRatesDTO() {
    }

    public PrivatBankExchangeRatesDTO(String date, String bank, Integer baseCurrency, String baseCurrencyLit, PrivatBankCurrenciesDTO[] exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "PrivatBankExchangeRatesDTO{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + Arrays.toString(exchangeRate) +
                '}';
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

    public PrivatBankCurrenciesDTO[] getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(PrivatBankCurrenciesDTO[] exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
