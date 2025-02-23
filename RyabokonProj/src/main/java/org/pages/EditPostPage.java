package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/edit-post/[a-zA-Z0-9]*";
    }

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveEditedPost;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//*[text()='Post successfully updated.']")
    private WebElement successMessage;


    public EditPostPage clickOnSaveEditedPostButton() {
        clickOnElement(buttonSaveEditedPost);
        return this;
    }
    public EditPostPage enterTextIntoInputTitle(String changedPostTitle) {
        clearAndEnterTextIntoElement(inputTitle, changedPostTitle);
        return this;
    }
    public EditPostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }
    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }
}