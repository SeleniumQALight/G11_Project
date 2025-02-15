package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends PostPage{

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement succesMessage;

    @FindBy(name = "title")
    private WebElement inputTitle;


    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;



    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage enterNewTextIntoInputTitle(String title) {
        clearAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdatedPost() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }


    public EditPostPage checkTextInSuccessMessage(String expectedMassageText) {
        checkTextInElement(succesMessage, expectedMassageText);
        return this;
    }


}