package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']"; //параметризованний локатор

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successDeletePostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    //findElements знайде всі елементи за таким локатором і поверне List (повний або пустий):

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(
                By.xpath(
                        String.format(postWithTitleLocator, postTitle)
                ));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkUrl();
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                numberOfPosts, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);

        final int MAX_POST_COUNT = 100;
        //postsList.size() або просто якась кінцева кількість ітерацій циклу, щоб він випадковово не зациклився

        int counter = 0; //кількість постів які видалили

        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePostPresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.warn("Number of posts with title " + postTitle + " more than " + MAX_POST_COUNT);
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePostPresent() {
        checkIsElementVisible(successDeletePostMessage);
        return this;
    }


}
