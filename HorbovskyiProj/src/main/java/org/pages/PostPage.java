package org.pages;

import org.apache.hc.core5.http.HeaderElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedInUserElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement uniquePostMessage;

    @FindBy(xpath = "//*[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";

    public HeaderForLoggedInUserElement getHeaderElement() {
        return new HeaderForLoggedInUserElement(webDriver);
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectOnPostPage() {
        checkUrlWithPattern();
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

    public PostPage checkIfUniquePost() {
        checkIsElementVisible(uniquePostMessage);
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
