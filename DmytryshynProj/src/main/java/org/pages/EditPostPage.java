package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
//    @FindBy(xpath = "//*[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;



    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

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


    public PostPage clickOnSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }



}
