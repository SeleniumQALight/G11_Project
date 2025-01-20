package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    WebDriver webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи, описані FindBy при створенні пейджі
        // (без цього кроку всі елементи будуть null, тобто ніколи не проініціалізуються)
        // кожного разу при зверненні до елементу його стан оновлюється
    }
}
