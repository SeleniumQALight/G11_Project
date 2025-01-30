package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
        //ініціалізує елементи, описані FindBy
        //інакше вони будуть Null
        //і вони також оновляться в той момент, коли до них буде звертання
    }

    //method for clearing and entering text into the element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //method for clicking on the element
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
                logger.info("Element isn't displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    //check if element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element isn't visible", isElementVisible(webElement));
    }

    //check if element is not visible
    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        Assert.assertEquals("Text in element is not expected", text, webElement.getText());
        logger.info("Text in element is expected");
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void makeCheckBoxSelected(WebElement webElement) {
        if (webElement.isSelected()) {
            logger.info("Checkbox is already checked");
        } else {
            webElement.click();
            logger.info("Checkbox is checked");
        }
    }

    protected void makeCheckBoxNotSelected(WebElement webElement) {
        if (webElement.isSelected()) {
            webElement.click();
            logger.info("Checkbox is unchecked");
        } else {
            logger.info("Checkbox isn't already selected");
        }
    }

    protected void checkboxState(WebElement webElement, String neededState) {
        switch (neededState) {
            case "check":
                makeCheckBoxSelected(webElement);
                break;
            case "uncheck":
                makeCheckBoxNotSelected(webElement);
                break;
        }
    }


}
