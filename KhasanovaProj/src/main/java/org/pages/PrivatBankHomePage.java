package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.PrivatBankTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankHomePage extends CommonActionsWithElements {

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRateButton;

    private Logger logger = Logger.getLogger(PrivatBankHomePage.class);

    protected String privatBankBaseUrl = "https://privatbank.ua/";

    private String buyRate = "//span[contains(text(), '%s ')]/ancestor::td/following-sibling::td[contains(@id, '_buy')]";
    private String sellRate = "//span[contains(text(), '%s ')]/ancestor::td/following-sibling::td[contains(@id, '_sell')]";

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getBuyRate(String currency) {
        return webDriver.findElement(By.xpath(String.format(buyRate, currency)));
    }

    public WebElement getSellRate(String currency) {
        return webDriver.findElement(By.xpath(String.format(sellRate, currency)));
    }

    public PrivatBankHomePage getExchangeRatesFromUi(String currency) {
        WebElement buyRateElement = getBuyRate(currency);
        WebElement sellRateElement = getSellRate(currency);

        PrivatBankTestData.BUY_RATE_UI = buyRateElement.getText();
        PrivatBankTestData.SELL_RATE_UI = sellRateElement.getText();

        logger.info("Rates for currency " + currency + " were got: " +
                "buy rate = " + PrivatBankTestData.BUY_RATE_UI + ", " +
                "sell rate = " + PrivatBankTestData.SELL_RATE_UI);
        return this;
    }

    public PrivatBankHomePage openPage() {
        webDriver.get(privatBankBaseUrl);
        logger.info("PrivatBank Home Page was opened with url " + privatBankBaseUrl);
        return this;
    }

    public void clickOnExchangeRateButton() {
        clickOnElement(exchangeRateButton);

    }

    public void checkExchangeRates() {
        SoftAssertions softAssertions = new SoftAssertions();

        double buyRateUi = Double.parseDouble(PrivatBankTestData.BUY_RATE_UI);
        double buyRateApi = Double.parseDouble(PrivatBankTestData.BUY_RATE_API);

        double sellRateUi = Double.parseDouble(PrivatBankTestData.SELL_RATE_UI);
        double sellRateApi = Double.parseDouble(PrivatBankTestData.SELL_RATE_API);
        logger.info(buyRateUi + " " + buyRateApi + " " + sellRateApi + " " + sellRateUi);
        softAssertions.assertThat(buyRateUi)
                .as("Buy rate from UI is not equal to buy rate from API")
                .isEqualTo(buyRateApi);

        softAssertions.assertThat(sellRateUi)
                .as("Sell rate from UI is not equal to sell rate from API")
                .isEqualTo(sellRateApi);

        softAssertions.assertAll();
    }
}
