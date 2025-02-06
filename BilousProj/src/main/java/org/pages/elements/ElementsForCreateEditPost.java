package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.PostPage;


public class ElementsForCreateEditPost extends CommonActionsWithElements {

    public ElementsForCreateEditPost(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(name="title")
    private WebElement inputTitle;

    @FindBy(id="post-body") // "//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input [@type='checkbox']")
    private WebElement checkboxIsPrivatePost;

    public ElementsForCreateEditPost enterTextIntoInputTitle(String title) {
        clearAndEnterTextInToElement(inputTitle, title);
        return new ElementsForCreateEditPost(webDriver);
    }

    public ElementsForCreateEditPost enterTextIntoInputBody(String body) {
        clearAndEnterTextInToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnButtonSavePost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public ElementsForCreateEditPost setOnCheckBoxIsPrivatePost() {
        setCheckboxState(checkboxIsPrivatePost, "check");
        return this;
    }


}
