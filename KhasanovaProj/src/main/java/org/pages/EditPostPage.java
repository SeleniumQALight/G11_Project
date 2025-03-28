package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//div[text()='Post successfully updated.']")
    private WebElement successUpdateMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new EditPostPage(webDriver);
    }

    public EditPostPage checkIsSuccessUpdateMessageDisplayed() {
        checkIsElementVisible(successUpdateMessage);
        return this;
    }

    public void checkTextInSuccessUpdateMessage(String expectedMessageText) {
        checkTextInElement(successUpdateMessage, expectedMessageText);
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }
}
