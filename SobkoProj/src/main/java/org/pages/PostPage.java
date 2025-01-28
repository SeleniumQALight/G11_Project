package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//*[text()='Is this post unique? : yes']")
    private WebElement stateYesOfUniquePost;

    @FindBy(xpath = "//*[text()='Is this post unique? : no']")
    private WebElement stateNoOfUniquePost;

    public PostPage(WebDriver webdriver) {
        super(webdriver);
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

    public PostPage checkIsPostUniqueCorrectState(String stateYesOrNo) {
        if (stateYesOrNo.equals("yes")) {
            Assert.assertTrue("State of unique post is not correct", isElementVisible(stateYesOfUniquePost));
        } else if (stateYesOrNo.equals("no")) {
            Assert.assertTrue("State of unique post is not correct", isElementVisible(stateNoOfUniquePost));
        } else {
            logger.error("Required state is not correct");
            Assert.fail("Required state is not correct");
        }
        return this;
    }


}
