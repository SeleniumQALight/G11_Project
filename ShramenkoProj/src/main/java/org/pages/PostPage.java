package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//a[text()='« Back to post permalink']")
    private WebElement toBackOnMyProfilePageLink;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement saveUpdatesButton;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@name='body']")
    private WebElement inputBody;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    //отримати доступ до всіх елементів з хедеру
    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check current url
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(deleteButton, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage clickOnEditButton() {
        clickOnElement(editButton, "Edit post button");
        return new PostPage(webDriver);
    }

    public PostPage clickOnSaveUpdatesButton(){
        clickOnElement(saveUpdatesButton,"Save Updates button");
        return new PostPage(webDriver);
    }

    public MyProfilePage clickOnBackToMyProfilePage() {
        clickOnElement(toBackOnMyProfilePageLink, "« Back to post permalink");
        return new MyProfilePage(webDriver);
    }

    public PostPage editTextIntoInputTitle(String title) {
        clearAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public PostPage editTextIntoInputBody(String body) {
        clearAndEnterTextIntoElement(inputBody, body);
        return this;
    }
}
