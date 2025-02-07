package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;
import org.pages.elements.MyProfilePage;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'New post successfully created.')]")
    private WebElement successMessage;
    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement uniquePostMessage;
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForUserElement  getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }
    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
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

    public PostPage checkTextInThisPostWasWrittenIsVisible(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        return this;
    }
}
