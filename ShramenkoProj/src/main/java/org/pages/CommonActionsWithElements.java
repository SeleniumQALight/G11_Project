package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    WebDriver webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
        //ініціалізує елементи, описані FindBy
        //інакше вони будуть Null
        //і вони також оновляться в той момент, коли до них буде звертання
    }


}
