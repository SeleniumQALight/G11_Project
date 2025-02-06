package org.pages;

import org.openqa.selenium.WebDriver;

public class EditPostPage extends ParantPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String getRelativeUrl() {
        return "/edit";
    }

    public EditPostPage checkIsRedirectOnEditPostPage() {
        checkUrl();
        return this;
    }
}
