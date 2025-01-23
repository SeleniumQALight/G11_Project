package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class CommonActionsWithElements {
    WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи, описані FindBy при створенні пейджі
        // (без цього кроку всі елементи будуть null, тобто ніколи не проініціалізуються)
        // кожного разу при зверненні до елементу його стан оновлюється
        //@CashLookUp - кешуємо елементи, які вже знайшли, і більше не шукаємо їх
    }

    // method for cleaning and entering text into element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    //check if element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    // method for clicking on element
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element is not as expected", text, webElement.getText());
        logger.info("Text in element is as expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

}