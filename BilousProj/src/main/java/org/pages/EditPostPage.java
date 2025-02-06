package org.pages;

import org.openqa.selenium.WebDriver;

public class EditPostPage extends ParantPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectOnEditPostPage() {
        checkUrlWithPattern();
        return this;
    }
}
