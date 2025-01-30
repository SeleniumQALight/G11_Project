package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForUserElement;
import org.apache.log4j.Logger;

public class PostPage extends ParentPage {
    // "//*[contains(@class,'alert-success')]"
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[text()='Is this post unique? : yes']")
    private WebElement checkboxUniquePostYes;

    @FindBy(xpath = "//p[text()='Is this post unique? : no']")
    private WebElement checkboxUniquePostNo;

    Logger logger = Logger.getLogger(getClass());

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForUserElement getHeaderForUserElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO checkUrl();
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