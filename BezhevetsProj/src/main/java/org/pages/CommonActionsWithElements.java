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
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FindBy
    }

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into the element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName;
        try {
            elementName = webElement.getAccessibleName();
        } catch (Exception e) {
            elementName = "";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            logger.error("Can not work with element " + elementName);
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element is not displayed");
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

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    //checkTextInElement
    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element " + getElementName(webElement) + " is not expected", text, webElement.getText());
        logger.info("Text in element " + getElementName(webElement) + " is expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    protected void setCheckboxAsChecked(WebElement webElement) {
        if (!webElement.isSelected()) {
            clickOnElement(webElement, "Unique post checkbox");
        } else {
            logger.info("Checkbox is already checked");
        }
    }

    protected void setCheckboxAsUnchecked(WebElement webElement) {
        if (webElement.isSelected()) {
            clickOnElement(webElement, "Unique post checkbox");
        } else {
            logger.info("Checkbox is already unchecked");
        }
    }

    protected void setCheckboxState(WebElement webElement, String state) {
        if (state.equalsIgnoreCase("check")) {
            setCheckboxAsChecked(webElement);
        } else if (state.equalsIgnoreCase("uncheck")) {
            setCheckboxAsUnchecked(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
            Assert.fail("State should be 'check' or 'uncheck'");
        }
    }


}
