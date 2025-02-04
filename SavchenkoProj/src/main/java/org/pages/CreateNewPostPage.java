package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    //@FindBy(xpath = "//input[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement uniquePostCheckbox;
    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO checkUrl();
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueInDD) {
        selectValueInDropDown(dropDownAccess, valueInDD);
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
return new PostPage(webDriver);
    }

    public CreateNewPostPage selectUniquePostCheckbox (String check){
        setCheckboxState(uniquePostCheckbox, check);
        return this;
    }

    public CreateNewPostPage checkIfUniquePost() {
        checkIsElementVisible(uniquePostCheckbox);
        return this;
    }
}
