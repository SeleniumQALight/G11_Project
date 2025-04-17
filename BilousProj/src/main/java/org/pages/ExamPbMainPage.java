package org.pages;

import org.apache.log4j.Logger;
import org.api.EndPointsPB;
import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExamPbMainPage extends ParantPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//ul[@class='new-style about']//button")
    private WebElement buttonExchangeRates;

    private String exchangeRatesBuy = "//td[@id='%s_buy']";
    private String exchangeRatesSell = "//td[@id='%s_sell']";

    public ExamPbMainPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    protected String getRelativeUrl() {
        return "";
    }
    public void openMainPagePB() {
        webDriver.get(EndPointsPB.HOME_PAGE_PB);
        logger.info("Login Page was opened with url " + EndPointsPB.HOME_PAGE_PB);
    }
    public void clickExchangeRatesButton() {
        clickOnElement(buttonExchangeRates);
        logger.info("Clicked on Exchange Rates button");

    }
    public void getExchangeRatesBuy(String currency) {
        WebElement elementExchangeRatesBuy =
                webDriver.findElement(By.xpath(String.format(String.valueOf(exchangeRatesBuy), currency.toUpperCase())));
        TestData.currencyBuyUi = Math.round(Double.parseDouble(elementExchangeRatesBuy.getText())*10)/10;
        System.out.println("BuyUI: " + TestData.currencyBuyUi);
        WebElement elementExchangeSaleBuy =
                webDriver.findElement(By.xpath(String.format(String.valueOf(exchangeRatesSell), currency.toUpperCase())));
        TestData.currencySaleUi = Math.round(Double.parseDouble(elementExchangeSaleBuy.getText())*10)/10;
        System.out.println("SaleUI: " + TestData.currencySaleUi);
    }
}
