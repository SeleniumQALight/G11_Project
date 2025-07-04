package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivatBankPage extends ParentPage {
    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRatesLink;

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }


    public PrivatBankPage openPage() {
        webDriver.get("https://privatbank.ua/");
        return this;
    }

    public PrivatBankExchangeRatesPage openExchangeRates() {
        webDriverWait_10.until(ExpectedConditions.elementToBeClickable(exchangeRatesLink));
        clickOnElementUsingActions(exchangeRatesLink);
        return new PrivatBankExchangeRatesPage(webDriver);
    }
}
