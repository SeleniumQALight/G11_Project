package org.pages;

import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PrivatBankExchangeRatesPage extends ParentPage{


    String locatorForSellRate = "//td[@id='%s_sell']";

    String locatorForBuyRate = "//td[@id='%s_buy']";


    public PrivatBankExchangeRatesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }


    public PrivatBankExchangeRatesPage getCurrencyRates(String currency) {
            TestData.CURRENCY_BUY_UI = Double.parseDouble(webDriver.findElement(By.xpath(String.format(locatorForBuyRate, currency))).getText());
            TestData.CURRENCY_SELL_UI = Double.parseDouble(webDriver.findElement(By.xpath(String.format(locatorForSellRate, currency))).getText());
        return this;
    }

}
