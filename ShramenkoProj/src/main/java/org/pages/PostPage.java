package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.log4j.Logger;

public class PostPage extends ParentPage {

@FindBy(xpath ="//*[contains(@class,'alert-success')]")
private WebElement successMessage;

@FindBy(xpath = "//p[text()='Is this post unique? : yes']")
private WebElement checkboxUniquePostYes;

@FindBy(xpath = "//p[text()='Is this post unique? : no']")
private WebElement checkboxUniquePostNo;

Logger logger = Logger.getLogger(getClass());

    public PostPage(WebDriver webDriver) {
        super(webDriver);
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

    public void checkStateUniquePost(){
        try {
            if (checkboxUniquePostYes.isDisplayed()) {
                logger.info("It's unique post");
            }
        } catch (Exception e) {
            logger.info("It's not unique post");
        }
    }


}
