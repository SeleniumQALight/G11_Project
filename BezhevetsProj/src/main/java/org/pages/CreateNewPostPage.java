package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParrentPage {
    @FindBy(name = "title") // @FindBy(xpath = "//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(id = "post-body") // @FindBy(xpath = "//*[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueInDropDown) {
        selectValueInDD(dropdownAccess, valueInDropDown);
        return this;
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}