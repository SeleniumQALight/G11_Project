package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement PostMessageUnique;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;




    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
//        TODO checkUrl();
        return this;
    }

    public PostPage checkIsSussesMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }

    public PostPage checkMessageUnique() {
        checkIsElementVisible(PostMessageUnique);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost, "Edit post button");
        return new CreateNewPostPage(webDriver);
    }



}



