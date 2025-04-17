package org.pages;

import org.apache.log4j.Logger;
import org.api.EndPointsPB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExamPbMainPage extends ParantPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//ul[@class='new-style about']//button")
    private WebElement buttonExchangeRates;

    @FindBy(xpath = "//td[@id='EUR_buy']")
    private WebElement exchangeRatesEURBuy;
    @FindBy(xpath = "//td[@id='EUR_sell']")
    private WebElement exchangeRatesEURSell;

    @FindBy(xpath = "//td[@id='USD_buy']")
    private WebElement exchangeRatesUSDBuy;
    @FindBy(xpath = "//td[@id='USD_sell']")
    private WebElement exchangeRatesUSDSell;

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

}
