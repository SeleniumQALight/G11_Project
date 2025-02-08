package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    //    @FindBy(xpath = "//*[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body") // @FindBy(xpath = "//*[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement checkbox;

    @FindBy(xpath = ".//select")
    private WebElement dropdownAccess;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;


    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }


    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueForSelect) {
        selectValueInDD(dropdownAccess, valueForSelect);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextIntoElement(inputBody, body);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage setCheckboxState(String state) {
        setStateToCheckbox(checkbox, state);
        return this;
    }

    public PostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }





}

