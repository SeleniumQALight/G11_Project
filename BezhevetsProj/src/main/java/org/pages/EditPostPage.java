package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParrentPage{
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(name = "title") // @FindBy(xpath = "//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(id = "post-body") // @FindBy(xpath = "//*[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdatedPost;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    @FindBy(xpath = "//input[@type='checkbox' and @name='uniquePost']")
    private WebElement checkboxUniquePost;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement succesMessage;

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage enterNewTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public EditPostPage clickOnSaveUpdatedPostButton() {
        clickOnElement(buttonSaveUpdatedPost);
        return this;
    }

    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(succesMessage);
        return this;
    }

    public EditPostPage checkTextInSuccessMessage(String expectedMassageText) {
        checkTextInElement(succesMessage, expectedMassageText);
        return this;
    }
}
