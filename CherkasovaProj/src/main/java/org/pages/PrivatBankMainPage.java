package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrivatBankMainPage extends CommonActionsWithElements {

    protected String privatBankBaseUrl = "https://privatbank.ua/";
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//li//button[@class='btn exchange-rate']")
    private WebElement buttonExchangeRate;

    public PrivatBankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String buyCurrency = "//td[@id='%s_buy']";
    protected String saleCurrency = "//td[@id='%s_sell']";


    public PrivatBankMainPage openPage() {
        webDriver.get(privatBankBaseUrl);
        logger.info("PrivatBank Main Page was opened with url " + privatBankBaseUrl);
        return this;
    }

    public void clickOnButtonExchangeRate() {
        clickOnElement(buttonExchangeRate);
    }

    public Double getBuyCurrencyRateUI(String currency) {
        return getCurrencyRate(buyCurrency, currency);
    }

    public Double getSaleCurrencyRateUI(String currency) {
        return getCurrencyRate(saleCurrency, currency);
    }

    private Double getCurrencyRate(String xpathPattern, String currency) {
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(xpathPattern, currency)));
        return new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "").trim())
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
