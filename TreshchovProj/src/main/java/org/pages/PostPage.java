package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";

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

    public PostPage checkTextThisPostWasWrittenIsVisible(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        return this;
    }

    public PostPage checkIsThisMessageUniqueText(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, "Is this post unique? : "+ text));
        return this;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage CheckIsAlertSuccessPresent() {
        checkIsElementVisible(successMessage);
        return this;
    }


    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnEditButton() {
        clickOnElement(buttonEdit, "Edit post button");
        return new CreateNewPostPage(webDriver);
    }
}
