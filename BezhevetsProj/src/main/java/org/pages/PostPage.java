package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParrentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement succesMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO checkUrl();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(succesMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMassageText) {
        checkTextInElement(succesMessage, expectedMassageText);
        return this;
    }
}