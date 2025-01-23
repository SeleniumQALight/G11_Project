package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParantPage {

//    @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name="title")
    private WebElement inputTitle;

    @FindBy(id="post-body") // "//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }


    // check is redirect on CreateNewPostPage
    public CreateNewPostPage checkIsRedirectOnCreateNewPostPage() {
        //TODO check url
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextInToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextInToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnButtonSavePost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
