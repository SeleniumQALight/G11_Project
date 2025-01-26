package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    //"//*[contains(@class,'alert-success')]"
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectOnPostPage() {
        //TODO checkURL();
        return this;
    }

    public PostPage checkIsSuccessMessagePresent() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }
}
