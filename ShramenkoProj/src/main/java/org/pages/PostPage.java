package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.log4j.Logger;
import org.pages.elements.HeaderForUserElement;

import java.util.List;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(),'%s')]";

//@FindBy(xpath = "//p[text()='Is this post unique? : yes']")
//private WebElement checkboxUniquePostYes;
//
//@FindBy(xpath = "//p[text()='Is this post unique? : no']")
//private WebElement checkboxUniquePostNo;

private String checkboxUniquePostLocator = "//p[text()='Is this post unique? : %s']";

Logger logger = Logger.getLogger(getClass());

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*"; //регулярний вираз [a-zA-Z0-9]* - будь-яка послідовність і кількість цифр і букв
    }

    //отримати доступ до всіх елементів з хедеру
    public HeaderForUserElement getHeaderElement() {
        return new HeaderForUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
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

    public PostPage checkTestThisPostWasWrittenIsVisible(String text) {
        checkIsElementVisible(String.format(locatorForTextThisPostWasWritten, text));
        return this;
    }

    public boolean checkStateUniquePost(String state) {
        try {
            WebElement checkboxUniquePost = webDriver.findElement(
                    By.xpath(
                            String.format(checkboxUniquePostLocator, state)
                    ));
            return isElementVisible(checkboxUniquePost);
        } catch (Exception e) {
            logger.error("Element not found");
            return false;
        }
    }

}
