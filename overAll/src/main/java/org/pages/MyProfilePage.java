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
        return "/profile/[a-zA-Z0-9]*";
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
        final int MAX_POST_COUNT = 100;  // postsList.size()
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;

        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("Number of posts with title " + postTitle + " more than " + MAX_POST_COUNT);
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementVisible(successMessageDelete);
        return this;
    }
}
