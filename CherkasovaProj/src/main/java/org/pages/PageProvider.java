package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage(){
        return  new HomePage(webDriver);
    }

    public PostPage getPostPage(){
        return new PostPage(webDriver);
    }

    public EditPostPage getEditPostPage(){
        return new EditPostPage(webDriver);
    }

    public CreateNewPostPage getCreateNewPostPage(){
        return new CreateNewPostPage(webDriver);
    }

    public MyProfilePage getMyProfilePage() { return new MyProfilePage(webDriver);
    }
}
