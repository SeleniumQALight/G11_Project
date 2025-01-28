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
        PageFactory.initElements(webDriver, this); // ініціалізує елементи, описані FindBy
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
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


    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    //checkTextInElement
    protected void checkTextInElement(WebElement webElement, String text) {
            Assert.assertEquals("Text in element not expected", text, webElement.getText());
            logger.info("Text in element is expected");

    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }


    protected void selectCheckbox(WebElement webElement) {
        if (webElement.isSelected() == false) {
            webElement.click();
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox is already selected");
        }
    }

    protected void unselectCheckbox(WebElement webElement) {
        if (webElement.isSelected() == true) {
            webElement.click();
            logger.info("Checkbox is unselected");
        } else {
            logger.info("Checkbox is already unselected");
        }
    }

    protected void setCheckboxState(WebElement webElement, String neededState) {
        boolean currentState = webElement.isSelected();
        if (neededState.equals("check")) {
            selectCheckbox(webElement);
        } else if (neededState.equals("uncheck")) {
            unselectCheckbox(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
        }
    }


}
