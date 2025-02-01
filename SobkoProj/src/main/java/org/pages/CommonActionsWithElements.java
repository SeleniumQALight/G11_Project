package org.pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {

    protected WebDriver webdriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webdriver) {

        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this); // ініціалізує елементи описані FindBy
    }

    //method for clearing and entering text into element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {

            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into input field");
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }

    }

    //method for clicking on element
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element ");
        Assert.fail("Can not work with element ");
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

    // check if element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        logger.error("Text in element not expected");
        Assert.assertEquals("Text in element not expected", text, webElement.getText());
        logger.info("Text in element is expected");

    }

    protected void setCheckedOrNotifyIfAlreadyChecked(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                logger.info("Checkbox has already been selected");
            } else {
                webElement.click();
                logger.info("Checkbox is selected");
            }
        } catch (Exception e) {
            logger.info("Element is not found");
        }
    }
    protected void setUncheckedOrNotifyIfAlreadyClear(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                webElement.click();
                logger.info("Checkbox is cleared");
            } else {
                logger.info("Checkbox is already cleared");
            }
        } catch (Exception e) {
            logger.info("Element is not found");
        }
    }

}
