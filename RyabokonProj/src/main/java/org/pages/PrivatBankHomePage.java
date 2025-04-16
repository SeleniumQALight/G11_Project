package org.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

public class PrivatBankHomePage extends CommonActionsWithElements {

    protected String privatBankBaseUrl = "https://privatbank.ua/";


    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement buttonExchangeRate;

    protected String buyCurrency = "//td[@id='%s_buy']";
    protected String saleCurrency = "//td[@id='%s_sell']";


    public PrivatBankHomePage openPage() {
        webDriver.get(privatBankBaseUrl);
        logger.info("PrivatBank Home Page was opened with url " + privatBankBaseUrl);
        return this;
    }

    public PrivatBankHomePage clickOnButtonExchangeRate() {
        clickOnElement(buttonExchangeRate);
        return this;

    }

    public Double getBuyCurrencyExchangeRateUI(String currency) {
        return getCurrencyExchangeRate(buyCurrency, currency);
    }

    public Double getSaleCurrencyExchangeRateUI(String currency) {
        return getCurrencyExchangeRate(saleCurrency, currency);
    }

    private Double getCurrencyExchangeRate(String xpathPattern, String currency) {
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(xpathPattern, currency)));
        return new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "").trim())
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
