package org.pages.browser;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.pages.CommonActionsWithElements;



public class BrowserActions extends CommonActionsWithElements {

    public BrowserActions(WebDriver webdriver) {
        super(webdriver);
    }

    private void printBrowserInterfaceActionError(Exception e) {
        logger.error("The browser interface element does`t work");
        Assert.fail("The browser interface element does`t work ");
    }

    public int getCurrentBrowserTabIndex() {
        int index = 0;
        try {
            String currentTab = webdriver.getWindowHandle();
            for (String tab : webdriver.getWindowHandles()) {
                if (tab.equals(currentTab)) {
                    break;
                }
                index++;
            }
            logger.info("Current tab index is " + index);
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
        return index;
    }

    public void switchToTabByIndex(int index) {
        try {
            Object[] windowHandles = webdriver.getWindowHandles().toArray();
            webdriver.switchTo().window((String) windowHandles[index]);
            logger.info("Switch to tab by index " + index);
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
    }

    protected void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webdriver);
            actions.moveToElement(webElement);
            actions.perform();
            logger.info("Scroll to element " + webElement.getAccessibleName());
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
    }

    public void openNewTab() {
        try {
            ((JavascriptExecutor) webdriver).executeScript("window.open()");
            webdriver.getWindowHandle();
            logger.info("New tab was opened");
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
    }

    public void closeCurrentTab() {
        try {
            webdriver.close();
            logger.info("Current tab was closed");
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
    }

    public void reloadPage() {
        try {
            webdriver.navigate().refresh();
            logger.info("Page was reloaded");
        } catch (Exception e) {
            printBrowserInterfaceActionError(e);
        }
    }


}
