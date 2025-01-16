package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {

    WebDriver webdriver;
    public CommonActionsWithElements(WebDriver webdriver) {

        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this); // ініціалізує елементи описані FindBy
    }
}
