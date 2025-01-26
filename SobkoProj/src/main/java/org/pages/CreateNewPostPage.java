package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webdriver) {
        super(webdriver);

    }
    @FindBy(name="title")
    private WebElement inputTitle;
    @FindBy(id="post-body")
    private WebElement inputBody;

    //check is redirect to CreateNewPostPage
    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        // TODO check url
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

    public CreateNewPostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return this;
    }

    public PostPage checkIsRedirectToPostPage() {
        return new PostPage(webdriver);
    }
}
