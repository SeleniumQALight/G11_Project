package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedInUserElement;

public class EditPostPage extends ParentPage {
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    //Save Updates
    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;


    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public HeaderForLoggedInUserElement getHeaderElement() {
        return new HeaderForLoggedInUserElement(webDriver);
    }

    public EditPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }
}
