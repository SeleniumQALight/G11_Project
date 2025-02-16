package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage {
    //    "//*contains [@class='alert-success']" -
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";
    private String locatorForTextIsThisPostUnique = "//*[contains(text(),'%s')]";


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage cheskIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete Post Button");
        return new MyProfilePage(webDriver);
    }

    public MyProfilePage clickOnEditButton() {
        clickOnElement(buttonEditPost, "Edit Post Button");
        return new MyProfilePage(webDriver);
    }


    public PostPage checkTextThisPostWasWrittenIsVisible(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        return this;
    }

    public PostPage checkTextIsThisPostUnique(String text) {
        checkIsElementVisible(String.format(locatorForTextIsThisPostUnique, text));
        return this;
    }
}
