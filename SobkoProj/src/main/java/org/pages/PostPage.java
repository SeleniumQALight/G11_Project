package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;


public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//*[text()='Is this post unique? : yes']")
    private WebElement stateYesOfUniquePost;

    @FindBy(xpath = "//*[text()='Is this post unique? : no']")
    private WebElement stateNoOfUniquePost;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;
    private String locatorForText = "//*[contains(text(),'%s')]";

    public PostPage(WebDriver webdriver) {
        super(webdriver);
    }

     public HeaderForUserElement getHeaderElement() {
         return new HeaderForUserElement(webdriver);
     }
    public PostPage checkIsRedirectToPostPage() {
        // TODO check url
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

    public PostPage checkIsPostUniqueCorrectState(String isUnique) {
        if (isUnique.equals("yes")) {
            checkIsElementVisible(stateYesOfUniquePost);
            checkIsElementNotVisible(stateNoOfUniquePost);
        } else if (isUnique.equals("no")) {
            checkIsElementVisible(stateNoOfUniquePost);
            checkIsElementNotVisible(stateYesOfUniquePost);
        } else {
            Assert.fail("State should be only 'yes' or 'no'");
        }
        return this;

    }

    public MyProfilePage clickOnDeleteButton() {
       clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webdriver);
    }

    public PostPage checkTextThisPostWasWrittenAndVisible(String text) {
     checkIsElementVisible(String.format(locatorForText, text));
        return this;
    }
}
