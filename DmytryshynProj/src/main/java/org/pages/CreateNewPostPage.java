package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
//    @FindBy(xpath = "//*[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body") // "//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkBox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkURL();
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueForSelect) {
        selectValueInDD(dropDownAccess, valueForSelect);
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

    public CreateNewPostPage clickOnCheckBoxIfNeed(String state) {
        setCheckBoxState(checkBox, state, "Unique Post Checkbox");
        return this;
    }

}
