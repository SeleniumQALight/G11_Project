package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.PrivatBankTestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivatBankHomePage {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(PrivatBankHomePage.class);
    private WebDriverWait webDriverWait15;

    protected String privatBankBaseUrl = "https://privatbank.ua/";

    private String buyRate = "//span[contains(text(), '%s ')]/ancestor::td/following-sibling::td[contains(@id, '_buy')]";
    private String sellRate = "//span[contains(text(), '%s ')]/ancestor::td/following-sibling::td[contains(@id, '_sell')]";

    public PrivatBankHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
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

        PrivatBankTestData.buyRateUi = buyRateElement.getText();
        PrivatBankTestData.sellRateUi = sellRateElement.getText();

        logger.info("Rates for currency " + currency + " were got: " +
                "buy rate = " + PrivatBankTestData.buyRateUi + ", " +
                "sell rate = " + PrivatBankTestData.sellRateUi);
        return this;
    }

    public PrivatBankHomePage openPage() {
        webDriver.get(privatBankBaseUrl);
        logger.info("PrivatBank Home Page was opened with url " + privatBankBaseUrl);
        return this;
    }

    public void clickOnExchangeRateButton() {
        try {
            WebElement exchangeRateButton = webDriver.findElement(By.xpath("//li//button[@class='btn exchange-rate']"));
            webDriverWait15.until(ExpectedConditions.visibilityOf(exchangeRateButton));
            exchangeRateButton.click();
            logger.info(exchangeRateButton + " Element was clicked");
        } catch (Exception e) {
            Assert.fail("Cannot work with element " + e);
        }
    }

    public void checkExchangeRates() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(PrivatBankTestData.buyRateUi)
                .as("Buy rate from UI is not equal to buy rate from API")
                .isEqualTo(PrivatBankTestData.buyRateApi.replaceAll("0+$", "").replaceAll("\\.$", ""));
        softAssertions.assertThat(PrivatBankTestData.sellRateUi)
                .as("Sell rate from UI is not equal to sell rate from API")
                .isEqualTo(PrivatBankTestData.sellRateApi.replaceAll("0+$", "").replaceAll("\\.$", ""));
        softAssertions.assertAll();
    }
}
