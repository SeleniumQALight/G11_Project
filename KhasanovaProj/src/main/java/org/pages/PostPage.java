package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[not(@*)]")
    private WebElement messageIsUniqueCheckbox;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath =  "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

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
        logger.info("Success message is displayed");
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }

    public PostPage checkIsPostUniqueCheckboxChecked(String expectedMessageText) {
        checkTextInElement(messageIsUniqueCheckbox, expectedMessageText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost, "Edit post button");
        return new PostPage(webDriver);
    }

}
