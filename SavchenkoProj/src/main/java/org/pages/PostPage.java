package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;
import org.pages.elements.MyProfilePage;

public class PostPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'New post successfully created.')]")
    private WebElement successMessage;
    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement uniquePostMessage;
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";

    private String locatorForUniquePost = ".//p[text()='Is this post unique? : %s']";

    @FindBy(xpath =  "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForUserElement  getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }
    public PostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        logger.info("Success message is displayed");
        return this;
    }

    public PostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost, "Edit post button");
        return new PostPage(webDriver);
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

    public PostPage checkPostUnique(String text ) {
        checkIsElementVisible(String.format(locatorForUniquePost, text));
        return this;
    }
}
