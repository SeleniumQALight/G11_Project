package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox' and @name='uniquePost']")
    private WebElement checkboxUniquePost;

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    //checkisredirrect to this page
    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        return this;
    }



    public CreateNewPostPage selectValueInDD(String value) {
        selectValueInDD(dropDownAccess, value);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    public CreateNewPostPage setCheckboxUniquePost() {
        checkBoxSelect(checkboxUniquePost, "check");
        return this;
    }

    public PostPage clickOnButtonSavePost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }



}
