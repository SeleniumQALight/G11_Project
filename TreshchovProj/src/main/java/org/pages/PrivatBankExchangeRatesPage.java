package org.pages;

import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankExchangeRatesPage extends ParentPage{

    @FindBy(xpath = "//div[@data-qa-value='cash']")
    private WebElement cashButton;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[1]")
    private WebElement exchangeRateEuroBuy;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[2]")
    private WebElement exchangeRateEuroSell;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[3]")
    private WebElement exchangeRateUSDBuy;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[4]")
    private WebElement exchangeRateUSDSell;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[5]")
    private WebElement exchangeRatePLNBuy;

    @FindBy(xpath = "(//div[@class='rate_kx9iSqCXBH'])[6]")
    private WebElement exchangeRatePLNSell;


    public PrivatBankExchangeRatesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    public PrivatBankExchangeRatesPage openPage() {
        webDriver.get("https://next.privat24.ua/exchange-rates");
        return this;
    }

    public PrivatBankExchangeRatesPage getCurrencyRates() {
        clickOnElement(cashButton);
        TestData.EUR_BUY_UI = Double.parseDouble(exchangeRateEuroBuy.getText());
        TestData.EUR_SELL_UI = Double.parseDouble(exchangeRateEuroSell.getText());
        TestData.USD_BUY_UI = Double.parseDouble(exchangeRateUSDBuy.getText());
        TestData.USD_SELL_UI = Double.parseDouble(exchangeRateUSDSell.getText());
        TestData.PLN_BUY_UI = Double.parseDouble(exchangeRatePLNBuy.getText());
        TestData.PLN_SELL_UI = Double.parseDouble(exchangeRatePLNSell.getText());
        return this;
    }

}
