package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private String postWithTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }


    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        return this;
    }


    public MyProfilePage checkPostWithTitlePresent(String title, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + title,
                numberOfPosts, getPostsList(title).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String title) {
        List<WebElement> postsList = getPostsList(title);
        final int MAX_POST_COUNT = 100;
        int count = 0;
        while (!postsList.isEmpty() && count < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage().checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + title + " was deleted");
            postsList = getPostsList(title);
            count++;

        }

        if (count >= MAX_POST_COUNT){
            logger.error("There are more than " + MAX_POST_COUNT + " posts with title " + title);
        }

        return this;
    }

    public MyProfilePage EditPostWithNewTitle(String currentTitle, String NewTitle){
        List<WebElement> postsList = getPostsList(currentTitle);
        clickOnElement(postsList.get(0));
        new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnEditButton()
                    .enterTextIntoInputTitle(NewTitle)
                    .clickOnButtonSaveUpdates()
                    .getHeaderElement().clickOnButtonMyProfile();
        logger.info("Post with title " + currentTitle + " was edited");
        return new MyProfilePage(webDriver);
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementVisible(successMessageDelete);
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}
