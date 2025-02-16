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

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//*[@data-icon='edit']")
    private WebElement buttonEditPost;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveEditedPost;

    private String locatorForTextThisPostWasWritten  = "//*[contains(text(),'%s')]";

    public HeaderForUserElements getHeaderElement() {
        return new HeaderForUserElements(webDriver);
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

    public PostPage checkIsRedirectToPostPage() {
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String text) {
    checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        //TODO
        return this;
    }

    public PostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost);
        return this;
    }

    public PostPage enterTextIntoInputTitle(String CHANGED_POST_TITLE) {
        clearAndEnterTextIntoElement(inputTitle, CHANGED_POST_TITLE);
        return this;
    }

    public PostPage clickOnSaveEditedPostButton() {
        clickOnElement(buttonSaveEditedPost);
        return this;
    }
}
