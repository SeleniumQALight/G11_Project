package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    //    @FindBy(xpath = "//*[@name='title']")
    @FindBy(name = "title") //спрощений варіант для елемента з локатором вище
    private WebElement inputTitle;

    @FindBy(id = "post-body")    //"//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxIsPostUnique;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
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

    //methode set needed state
    public CreateNewPostPage setNeededStateToPostUniqueCheckBox(String neededState) {
        setNeededStateToCheckBox(checkBoxIsPostUnique, neededState);

        return this;
    }

    public CreateNewPostPage selectValueInDropdownAccess(String valueForSelect) {
        selectValueInDD(dropdownAccess, valueForSelect);
        return this;
    }
}
