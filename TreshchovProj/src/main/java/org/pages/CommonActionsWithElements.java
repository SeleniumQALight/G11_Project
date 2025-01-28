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
        PageFactory.initElements(webDriver, this); //initialize elements described in FindBy
    }


    protected void enterTextIntoInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    protected void clickOnElement(WebElement element) {
        try {
            element.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }


    protected boolean isElementVisible(WebElement element) {
        try {
            boolean tempState = element.isDisplayed();
            if (tempState) {
                logger.info("Element is visible");
            } else {
                logger.info("Element is not visible");
            }
            return tempState;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }


    protected void checkIsElementVisible(WebElement element) {
        Assert.assertTrue("Element is not visible", isElementVisible(element));
    }

    protected void checkIsElementNotVisible(WebElement element) {
        Assert.assertFalse("Element is visible", isElementVisible(element));
    }

    protected void checkTextInElement(WebElement element, String text) {
            Assert.assertEquals("Text in element is not expected", text, element.getText());
            logger.info("Text in element is expected");
    }

    protected void checkBoxCheck(WebElement element) {
        try {
            boolean tempState = element.isSelected();
            if (tempState) {
                logger.info("Checkbox is now selected");
            } else {
                logger.info("Checkbox is selected");
                element.click();
            }
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }
    }

    protected void checkBoxUncheck(WebElement element) {
        try {
            boolean tempState = element.isSelected();
            if (tempState) {
                logger.info("Checkbox is now unselected");
                element.click();
            } else {
                logger.info("Checkbox is not selected");
            }
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }
    }

    protected void checkBoxSelect(WebElement element, String state){
        boolean tempState = element.isSelected();
        if (state.equals("check")){
            checkBoxCheck(element);
        } else if (state.equals("uncheck")){
            checkBoxUncheck(element);
        } else {
            logger.info("State is not correct");
        }
    }


}
