package org.pages;

import org.apache.log4j.Logger;
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

    public String getRateFromUI(String nameOfExchange) {
        String rateUI;
        clickOnElement(buttonExchangeRateInHeader, "ButtonExchangeRateInHeader");
        WebElement rateElement = webDriver.findElement(By.xpath(String.format(rateBuy, nameOfExchange)));

        rateUI = new BigDecimal(rateElement.getText().replaceAll("[^0-9.]", "")
                .trim()).setScale(5, RoundingMode.HALF_UP).toString();

        logger.info("Значення курсу UI для " + nameOfExchange + " після обробки: " + rateUI);
        return rateUI;
    }

    public PbHomePage openPage() {
        webDriver.manage().window().maximize();
        webDriver.get(baseUrl);
        logger.info("Page was opened with url " + baseUrl);
        return this;
    }
}
