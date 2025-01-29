package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані FindBy
    }

    // method for cleaning and entering text into element
    protected void clearAndEnterTextInToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
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
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementInvisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    // checkTextInElement
    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element not expected", text, webElement.getText());
        logger.info("Text in element is expected");
    }

    protected void selectCheckbox(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void deselectCheckbox(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was deselected");
            } else {
                logger.info("Checkbox is already deselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxState(WebElement checkbox, String state) {
        try {
            if ("check".equalsIgnoreCase(state)) {
                selectCheckbox(checkbox);
                logger.info("This post is unique");
            } else if ("uncheck".equalsIgnoreCase(state)) {
                deselectCheckbox(checkbox);
                logger.info("This post is not unique");
            } else {
                logger.error("Invalid state: " + state);
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }



    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
