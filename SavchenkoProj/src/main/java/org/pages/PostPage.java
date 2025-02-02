package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'New post successfully created.')]")
    private WebElement successMessage;
    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement uniquePostMessage;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement  getHeaderElement() {
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

    public PostPage checkTextInSuccessMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }
    public PostPage checkIfUniquePost() {
        checkIsElementVisible(uniquePostMessage);
        return this;
    }
}
