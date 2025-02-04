package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
    // "//*[contains(@class,'alert-success')]"
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderForUserElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
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
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        return this;
    }
}
