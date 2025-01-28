package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = "//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getPostsTitles(String postTitle) {
        return webDriver.findElements(
                By.xpath(
                        String.format(postTitleLocator, postTitle)
                )
        );
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check current URL
        return this;

    }

    public MyProfilePage checkIsPostWithTitleWereAdded(String postTitle, int numberOfPost) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                numberOfPost,
                getPostsTitles(postTitle).size()
        );
        return this;
    }
}
