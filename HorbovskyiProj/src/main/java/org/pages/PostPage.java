package org.pages;

import org.apache.hc.core5.http.HeaderElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedInUserElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public HeaderForLoggedInUserElement getHeaderElement() {
        return new HeaderForLoggedInUserElement(webDriver);
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectOnPostPage() {
        //TODO checkUrl();
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnDeleteButton();
        return new MyProfilePage(webDriver);
    }
}
