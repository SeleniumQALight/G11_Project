package org.loginTest;

import org.baseTest.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithNewTab extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void T00015_loginTestWithNewTab() {
        pageProvider.getLoginPage().openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        // Перевірити, що кнопка SignOut видима
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Відкрити нову вкладку через JavaScript
        pageProvider.getHomePage().openNewTab();

        // Перейти на нову вкладку (індекс 1)
        pageProvider.getHomePage().switchToTab(1);

        // Відкрити сторінку логіну
        pageProvider.getLoginPage().openPage();

        // Перевірити, що кнопка SignOut видима
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Перейти на головну вкладку (індекс 0)
        pageProvider.getHomePage().switchToMainTab();

        // Перевірити, що кнопка SignOut видима
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        // Закрити нову вкладку
        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().closeCurrentTab();

        // Перейти на головну вкладку
        pageProvider.getHomePage().switchToMainTab();

        // Перевірити, що кнопка SignOut видима
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }
}