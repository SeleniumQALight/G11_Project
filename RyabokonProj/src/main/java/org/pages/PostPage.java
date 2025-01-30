package org.pages;

import org.apache.hc.core5.http.HeaderElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElements;

public class PostPage extends ParentPage {
    //"//*[contains(@class,'alert-success')]"
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElements getHeaderElement() {
        return new HeaderForUserElements(webDriver);
    }
    public PostPage checkIsRedirectOnPostPage() {
        //TODO checkURL();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }
}
