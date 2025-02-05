package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.log4j.Logger;
import java.util.List;


public class MyProfilePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";
    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webdriver) {
        super(webdriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-z0-9]*";
    }


    private List<WebElement> getPostList(String postTitle) {
        return webdriver.findElements(By.xpath(String.format(postWithTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, numberOfPosts, getPostList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postsList = getPostList(postTitle);
        final int MAX_ITERATION = 100;
        int iteration = 0;
        while (!postsList.isEmpty()) {
            clickOnElement(postsList.get(0));
            new PostPage(webdriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostList(postTitle);
            iteration++;
        }
        if(iteration == MAX_ITERATION){
           logger.error(MAX_ITERATION + " posts with title " + postTitle + " were deleted");
        }
        return this;
    }



    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementVisible(successMessageDelete);
        return this;
    }
}
