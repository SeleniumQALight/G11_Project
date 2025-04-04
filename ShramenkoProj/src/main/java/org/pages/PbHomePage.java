package org.pages;

import org.apache.log4j.Logger;
import org.data.TestDataPB;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PbHomePage extends CommonActionsWithElements {
    protected String baseUrl = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    private WebElement buttonExchangeRateInHeader;

    public PbHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    private String rateBuy = "//td[@id='%s_buy']";
    private String rateSell = "//td[@id='%s_sell']";

    public void clickOnButtonExchangeInHeader(){
        clickOnElement(buttonExchangeRateInHeader, "ButtonExchangeRateInHeader");
    }

    public Double getRateBuyFromUI(String nameOfExchange) {
        Double rateUiBuy;
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(rateBuy, nameOfExchange)));

        rateUiBuy = new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "")
                .trim()).setScale(5, RoundingMode.HALF_UP).doubleValue();

        TestDataPB.setRateUiBuy(rateUiBuy);
        return rateUiBuy;
    }

    public Double getRateSellFromUI(String nameOfExchange) {
        Double rateUiSell;
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(rateSell, nameOfExchange)));

        rateUiSell = new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "")
                .trim()).setScale(5, RoundingMode.HALF_UP).doubleValue();

        TestDataPB.setRateUiSell(rateUiSell);
        return rateUiSell;
    }

    public PbHomePage openPage() {
        webDriver.manage().window().maximize();
        webDriver.get(baseUrl);
        return this;
    }
}
