package org.api.dto.response;

import java.util.List;

public class ExchangePrivateBankDTO {

    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRatedDTO> exchangeRate;

    public ExchangePrivateBankDTO() {
    }

    public ExchangePrivateBankDTO(String date,String bank,Integer baseCurrency,String baseCurrencyLit, List<ExchangeRatedDTO> exchangeRate){

        this.date = date;
        this.exchangeRate = exchangeRate;
        this.baseCurrencyLit = baseCurrencyLit;
        this.baseCurrency = baseCurrency;
        this.bank = bank;
    }

    public List<ExchangeRatedDTO> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<ExchangeRatedDTO> exchangeRate) {
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


    public String toString() {
        return "ExchangePrivateBankDTO {" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';

    }

}


