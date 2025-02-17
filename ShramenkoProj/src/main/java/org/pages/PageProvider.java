package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }

    public MyProfilePage getMyProfilePage() {
        return new MyProfilePage(webDriver);
    }

    public void openNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        logger.info("New Tab is opened");
        switchToTab();
    }

    public void switchToTab() {
        String originalHandle = webDriver.getWindowHandle(); //поточне активне вікно
        for (String handle : webDriver.getWindowHandles()) { //перебирає всі відкриті вкладки і зберігає їх в сет
            if (!handle.equals(originalHandle)) { //умова знайти вкладку яка відрізняється від поточної
                webDriver.switchTo().window(handle); //перехід на цю вкладку
                logger.info("Switch to another tab");
                break;
            }
        }
    }

    public void closeNotActualTabAndReturnOnMainTab() {
        String originalHandle = webDriver.getWindowHandle();
        String tabToClose = null; // Зберігаємо handle вкладки для закриття

        for (String handle : webDriver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                tabToClose = handle;
                break;
            }
        }

        if (tabToClose != null) { // Перевіряємо, чи була знайдена вкладка для закриття
            webDriver.switchTo().window(tabToClose); // Перемикаємось на вкладку для закриття
            webDriver.close();
            logger.info("Close not actual tab");
            webDriver.switchTo().window(originalHandle); // Повертаємось на original вкладку!!!
            logger.info("Switch back to original tab");
        } else {
            logger.info("No not actual tab found");
        }
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

}
