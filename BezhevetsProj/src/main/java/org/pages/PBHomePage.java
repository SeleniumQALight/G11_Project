package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

public class PBHomePage extends CommonActionsWithElements{
    public PBHomePage(WebDriver webDriver) {
        super(webDriver);
    }
    private Logger logger = Logger.getLogger(getClass());

    protected String PB_URL = "https://privatbank.ua/";

    @FindBy(xpath = "//header//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRateButton;

    public PBHomePage openPage() {
        webDriver.get(PB_URL);
        logger.info("Home page was opened");
        return this;
    }

    public PBHomePage clickOnExchangeRateButton() {
        clickOnElement(exchangeRateButton, "Exchange Rate Button");
        logger.info("Exchange rate button was clicked");
        return this;
    }

    public double getBuyRate(String currency) {
        return parseRate(String.format("//td[@id='%s_buy']", currency));
    }

    public double getSellRate(String currency) {
        return parseRate(String.format("//td[@id='%s_sell']", currency));
    }

    private double parseRate(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement rateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            String text = rateElement.getText();
            String digitsOnly = text.replaceAll("[^0-9.]", "").trim();

            if (digitsOnly.isEmpty()) {
                logger.error("No numeric value found at xpath: " + xpath + ". Original text: '" + text + "'");
                return 0.0;
            }

            return new BigDecimal(digitsOnly).setScale(5, RoundingMode.HALF_UP).doubleValue();
        } catch (TimeoutException e) {
            logger.error("Element not found or not visible within timeout for xpath: " + xpath, e);
            return 0.0;
        } catch (Exception e) {
            logger.error("Failed to parse rate from xpath: " + xpath, e);
            return 0.0;
        }
    }

}
