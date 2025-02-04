package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParrentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement succesMessage;

    //Locator for delete button
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//*[text()='Is this post unique? : yes']")
    private WebElement postIsUnique;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO checkUrl();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(succesMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMassageText) {
        checkTextInElement(succesMessage, expectedMassageText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsPostIsUnique() {
        checkIsElementVisible(postIsUnique);
        return this;
    }
}