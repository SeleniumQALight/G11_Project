package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private String postWithTitleLocator = "//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO
        return this;
    }


    public MyProfilePage checkPostWithTitlePresent(String title, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + title,
                numberOfPosts, getPostsList(title).size());
        return this;
    }
}
