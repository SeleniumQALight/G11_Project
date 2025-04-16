package org.pages;

import org.apache.log4j.Logger;
import org.data.TestDataPrivatBank;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrivatBankHomePage extends CommonActionsWithElements {
    protected String baseUrl = "https://privatbank.ua/";

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRateButton;

    private String buyRate = "//td[@id='%s_buy']";
    private String sellRate = "//td[@id='%s_sell']";

    public PrivatBankHomePage openPage() {
        webDriver.get(baseUrl);
        logger.info("Open page: " + baseUrl);
        return this;
    }

    public PrivatBankHomePage clickExchangeRateButton() {
        clickOnElement(exchangeRateButton);
        return this;
    }

    public Double getBuyRateFromUI(String nameOfExchange) {
        Double rateUiBuy;
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(buyRate, nameOfExchange)));

        rateUiBuy = new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "")
                .trim()).setScale(5, RoundingMode.HALF_UP).doubleValue();

        TestDataPrivatBank.setRateUiBuy(rateUiBuy);
        return rateUiBuy;
    }

    public Double getSellRateFromUI(String nameOfExchange) {
        Double rateUiSell;
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(sellRate, nameOfExchange)));

        rateUiSell = new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "")
                .trim()).setScale(5, RoundingMode.HALF_UP).doubleValue();

        TestDataPrivatBank.setRateUiSell(rateUiSell);
        return rateUiSell;
    }

}
