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

    private String postTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDeletePost;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]+";
    }

    public List<WebElement> getPostsTitles(String postTitle) {
        return webDriver.findElements(
                By.xpath(
                        String.format(postTitleLocator, postTitle)
                )
        );
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        return this;

    }

    public MyProfilePage checkIsPostWithTitleWereAdded(String postTitle, int numberOfPost) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                numberOfPost,
                getPostsTitles(postTitle).size()
        );
        return this;
    }

    public MyProfilePage deletePostWhilePresent(String postTitle) {
        List<WebElement> posts = getPostsTitles(postTitle);
        final int MAX_POST_COUNT = 100;
        int count = 0;
        while (!posts.isEmpty() && (MAX_POST_COUNT > count)) {
            clickOnElement(posts.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectOnPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessagePostWasDeleted();
            logger.info("Post with title " + postTitle + " was deleted");
            posts = getPostsTitles(postTitle);
        }
        if (MAX_POST_COUNT == count) {
            logger.error("Number of posts more than " + MAX_POST_COUNT);
        }

        return this;
    }

    private MyProfilePage checkIsMessagePostWasDeleted() {
        checkIsElementVisible(successMessageDeletePost);
        return this;
    }
}
