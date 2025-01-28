package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@name='body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO check current url
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

    public CreateNewPostPage makeSelectedCheckboxUniquePost() {
        makeCheckBoxSelected(checkboxUniquePost);
        return this;
    }

    public CreateNewPostPage makeNotSelectedCheckboxUniquePost() {
        makeCheckBoxNotSelected(checkboxUniquePost);
        return this;
    }

    public CreateNewPostPage checkboxUniqueState(String neededState) {
        checkboxState(checkboxUniquePost, neededState);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


}
