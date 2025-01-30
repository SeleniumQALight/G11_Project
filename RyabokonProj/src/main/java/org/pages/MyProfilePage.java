package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    private String postWithTitleLocator = "//*[text() ='%s']";


    private List<WebElement> postsWithTitle(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle)));
    }

        public MyProfilePage checkIsRedirectOnMyProfilePage() {
        //TODO checkURL();
        return this;
    }
        public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int numberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                numberOfPosts, postsWithTitle(postTitle).size());
            //TODO
            return this;
        }
    }

