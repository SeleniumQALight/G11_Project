package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.ElementsForCreateEditPost;

public class EditPostPage extends ParantPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successUpdatedMessage;


    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectOnEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public ElementsForCreateEditPost getElementsForCreateEditPost() {
        return new ElementsForCreateEditPost(webDriver);
    }
    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successUpdatedMessage);
        return this;
    }

    public EditPostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successUpdatedMessage, expectedMessageText);
        return this;
    }




}
