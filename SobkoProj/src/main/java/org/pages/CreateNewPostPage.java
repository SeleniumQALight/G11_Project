package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement uniquePostCheckbox;
    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;


    public CreateNewPostPage(WebDriver webdriver) {
        super(webdriver);

    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    @FindBy(name="title")
    private WebElement inputTitle;
    @FindBy(id="post-body")
    private WebElement inputBody;

    public CreateNewPostPage selectValueInDropDownAccess(String valueForSelect) {
        selectValueInDD(dropdownAccess, valueForSelect);
        return this;
    }
    //check is redirect to CreateNewPostPage
    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
       checkUrlWithPattern();
       return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextIntoElement(inputBody, body);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webdriver);
    }

    public CreateNewPostPage setUniquePostCheckboxState (String requiredState) {
        if (requiredState.equals("check")) {
            setCheckedOrNotifyIfAlreadyChecked(uniquePostCheckbox);
        } else if (requiredState.equals("uncheck")) {
            setUncheckedOrNotifyIfAlreadyClear(uniquePostCheckbox);
        } else {
            logger.error("Required state is not correct");
        }
        return this;
    }


}
