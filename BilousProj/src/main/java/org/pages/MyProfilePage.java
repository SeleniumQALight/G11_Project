package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParantPage {

    private Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";
    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete ;

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
        return webDriver.findElements(By.xpath(String.format(postWithTitleLocator, postTitle)));
    }


    public MyProfilePage checkIsRedirectOnMyProfilePage() {
        checkUrlWithPattern();
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, numberOfPosts, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        final int MAX_ITERATION = 100; // postList.size()
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_ITERATION) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectOnPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectOnMyProfilePage()
                    .checkIsSuccessMessageDeletePresent();
                    logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;
        }
        if (counter >= MAX_ITERATION) {
            logger.error("There are more than " + MAX_ITERATION + " posts with title " + postTitle);
        }
        return this;
    }

    private MyProfilePage checkIsSuccessMessageDeletePresent() {
        checkIsElementVisible(successMessageDelete);
        return this;
    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(getPostsList(postTitle).get(0));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}
