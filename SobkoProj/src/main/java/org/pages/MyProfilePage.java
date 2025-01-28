package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postWithTitleLocator = "//*[text()='%s']";
    public MyProfilePage(WebDriver webdriver) {
        super(webdriver);
    }


    private List<WebElement> getPostList(String postTitle) {
        return webdriver.findElements(By.xpath(String.format(postWithTitleLocator, postTitle)));
    }
    public MyProfilePage checkIsRedirectToMyProfilePage() {

        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, numberOfPosts, getPostList(postTitle).size());
        return this;
    }

}
