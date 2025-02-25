package org.loginTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithNewTab extends BaseTest {
    @Test
    public void T00015_loginTestWithNewTab() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.open('about:blank','_blank');");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        pageProvider.getLoginPage().openPage();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        driver.switchTo().window(tabs.get(0));
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        driver.switchTo().window(tabs.get(1)).close();
        driver.switchTo().window(tabs.get(0));
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();    }
}
