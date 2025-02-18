package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;



    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z-9]*";
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postWithTitleLocator, postTitle)
        ));
    }

    public MyProfilePage checkIsRedirectToProfilePage() {
       checkUrlWithPattern();
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, numberOfPosts, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        final int MAX_POSTS_COUNT = 100; // to prevent infinite loop
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POSTS_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);

        }
        if (counter >= MAX_POSTS_COUNT) {
            logger.error("There are more than " + postTitle + "more than " + MAX_POSTS_COUNT + " posts");
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementVisible(successMessageDelete);
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String POST_TITLE) {
        clickOnElement(getPostsList(POST_TITLE).get(0));
        return this;
    }


}